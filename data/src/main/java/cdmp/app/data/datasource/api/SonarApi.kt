package cdmp.app.data.datasource.api

import androidx.annotation.WorkerThread
import cdmp.app.domain.model.User
import cdmp.app.data.datasource.SonarDataSource
import cdmp.app.data.service.SonarService
import cdmp.app.domain.model.UserLogin

class SonarApi(private val service: SonarService) : SonarDataSource {

    @WorkerThread
    override suspend fun logUser(user: UserLogin) {
        service.logUser(user)
    }

}