package cdmp.app.sonar.di

import cdmp.app.domain.case.LogUserCase
import cdmp.app.domain.case.ObserveMessagesCase
import cdmp.app.domain.case.SendMessageCase
import cdmp.app.domain.case.SubscribeToChannelCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DomainModules {
    val useCaseModules = module {
        single {
            LogUserCase(
                get(named(DiModuleBuilder.REAL_IMPL)),
                Dispatchers.IO
            )
        }

        single {
            SubscribeToChannelCase(
                get(named(DiModuleBuilder.REAL_IMPL)),
                Dispatchers.IO
            )
        }

        single {
            SendMessageCase(
                get(named(DiModuleBuilder.REAL_IMPL)),
                get(),
                Dispatchers.IO
            )
        }

        single {
            ObserveMessagesCase(
                get(named(DiModuleBuilder.REAL_IMPL)),
                get(),
                Dispatchers.IO
            )
        }

    }
}