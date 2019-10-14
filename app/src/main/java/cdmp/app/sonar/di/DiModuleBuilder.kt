package cdmp.app.sonar.di

object DiModuleBuilder {

    const val REAL_IMPL = "REAL_IMPL"

    fun buildModules() = listOf(
//        DataModules.remoteServiceModule,
//        DataModules.apiModule,
//        DataModules.dbModule,
//        DataModules.repoModule,
//        DomainModules.useCaseModules,
        PresentationModules.mainModule
    )

}


