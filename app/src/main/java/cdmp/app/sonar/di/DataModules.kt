package cdmp.app.sonar.di

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.Geocoder
import android.location.LocationManager
import cdmp.app.data.LocationService
import cdmp.app.data.SonarSocketConnection
import cdmp.app.data.datasource.SonarDataSource
import cdmp.app.data.datasource.api.SonarApi
import cdmp.app.data.model.UserCache
import cdmp.app.data.repo.ChatRepo
import cdmp.app.data.repo.UserRepo
import cdmp.app.data.service.BaseUrl
import cdmp.app.data.service.SonarService
import cdmp.app.data.service.SonarSocketService
import cdmp.app.domain.repo.ChatRepoContract
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*
import java.util.concurrent.TimeUnit


object DataModules {

    val remoteServiceModule = module {
        single {
            OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS).build()
        }

        single {
            createWebService<SonarService>(
                get(),
                BaseUrl
            )
        }
        single {
            Scarlet.Builder()
                .webSocketFactory(get<OkHttpClient>().newWebSocketFactory("$BaseUrl/channel-session"))
                .addMessageAdapterFactory(MoshiMessageAdapter.Factory())
                .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
                .build()
                .create(SonarSocketService::class.java)
        }
    }

    val apiModule = module {
        single {
            SonarApi(get()) as SonarDataSource
        }

        single {
            SonarSocketConnection(get())
        }
    }


    @SuppressLint("MissingPermission")
    val localModule = module {
        //        single {
//            Room.databaseBuilder(
//                androidContext(),
//                RickMortyDb::class.java,
//                "Database"
//            ).build() as SonarDataSource.Mutable
//        }
        single {

            val locationManager = androidContext()
                .getSystemService(LOCATION_SERVICE) as LocationManager
            val locationService = LocationService(
                Geocoder(androidContext(), Locale.getDefault()),
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            )

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10f, locationService
            )

            locationService
        }
    }

    val repoModule = module {
        single(named(DiModuleBuilder.REAL_IMPL)) {
            UserRepo(get(), get(), UserCache())
        }

        single {
            ChatRepo(get(), get(), get()) as ChatRepoContract
        }
    }
}
