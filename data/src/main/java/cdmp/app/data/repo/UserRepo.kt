package cdmp.app.data.repo

import androidx.annotation.WorkerThread
import cdmp.app.data.SonarSocketConnection
import cdmp.app.data.datasource.SonarDataSource
import cdmp.app.data.model.UserCache
import cdmp.app.domain.model.MessageRequest
import cdmp.app.domain.model.UserLogin
import cdmp.app.domain.repo.UserRepoContract

class UserRepo(
    private val api: SonarDataSource,
    private val socket: SonarSocketConnection,
    private val cache: UserCache
) : UserRepoContract {

    override suspend fun getUserID(): String {
        return cache.userID
    }

    override suspend fun subscribeToChannel(message: MessageRequest): Boolean {
        return socket.sendMessage(message)
    }


    @WorkerThread
    override suspend fun logUser(user: UserLogin) {
        api.logUser(user)
        cache.userID = user.token
    }


}