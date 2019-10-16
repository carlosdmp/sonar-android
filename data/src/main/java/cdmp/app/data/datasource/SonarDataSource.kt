package cdmp.app.data.datasource

import androidx.annotation.WorkerThread
import cdmp.app.domain.model.User
import cdmp.app.domain.model.UserLogin
import retrofit2.http.Body

interface SonarDataSource {

    interface Mutable : SonarDataSource {
//        @WorkerThread
//        suspend fun addPage(page: CharacterPageEntity)
    }

    @WorkerThread
    suspend fun logUser(@Body user: UserLogin): Unit

}