package cdmp.app.sonar.di

import cdmp.app.presentation.viewmodel.SessionViewModel
import cdmp.app.presentation.viewmodel.viewModelScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object PresentationModules {
    val mainModule = module {
        viewModel {
            SessionViewModel(
                loginCase = get(),
                scope = viewModelScope(),
                subscribeToChatCase = get()
            )
        }
    }
}

