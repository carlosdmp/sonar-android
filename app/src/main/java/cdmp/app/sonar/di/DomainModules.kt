package cdmp.app.sonar.di

import cdmp.app.domain.case.LogUserCase
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
    }
}