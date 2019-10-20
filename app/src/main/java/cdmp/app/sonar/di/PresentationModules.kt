package cdmp.app.sonar.di

import android.icu.lang.UCharacter
import cdmp.app.presentation.viewmodel.ChatViewModel
import cdmp.app.presentation.viewmodel.SessionViewModel
import cdmp.app.presentation.viewmodel.viewModelScope
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object PresentationModules {
    val mainModule = module {
        viewModel {
            SessionViewModel(
                loginCase = get(),
                scope = viewModelScope(),
                subscribeToChatCase = get()
            )
        }
        viewModel {
            ChatViewModel(
                sendMessageCase = get(),
                observeMessagesCase = get(),
                scope = viewModelScope()
            )
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