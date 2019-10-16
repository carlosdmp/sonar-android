package cdmp.app.sonar.di

import cdmp.app.data.SonarSocketConnection
import cdmp.app.data.datasource.SonarDataSource
import cdmp.app.data.datasource.api.SonarApi
import cdmp.app.data.repo.UserRepo
import cdmp.app.data.service.BaseUrl
import cdmp.app.data.service.SonarService
import cdmp.app.data.service.SonarSocketService
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
                .webSocketFactory(get<OkHttpClient>().newWebSocketFactory("$BaseUrl/channel"))
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


//    val dbModule = module {
//        single {
//            Room.databaseBuilder(
//                androidContext(),
//                RickMortyDb::class.java,
//                "Database"
//            ).build() as SonarDataSource.Mutable
//        }
//    }

    val repoModule = module {
        single(named(DiModuleBuilder.REAL_IMPL)) {
            UserRepo(get(), get())
        }
    }
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}