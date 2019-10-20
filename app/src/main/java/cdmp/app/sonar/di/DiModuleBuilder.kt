package cdmp.app.sonar.di

object DiModuleBuilder {

    const val REAL_IMPL = "REAL_IMPL"

    fun buildModules() = listOf(
        DataModules.remoteServiceModule,
        DataModules.apiModule,
        DataModules.repoModule,
        DataModules.localModule,
        DomainModules.useCaseModules,
        PresentationModules.mainModule
    )

}


