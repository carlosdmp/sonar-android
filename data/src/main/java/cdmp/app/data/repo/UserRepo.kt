package cdmp.app.data.repo

import androidx.annotation.WorkerThread
import cdmp.app.data.SonarSocketConnection
import cdmp.app.data.datasource.SonarDataSource
import cdmp.app.domain.model.SubscribeMessage
import cdmp.app.domain.model.UserLogin
import cdmp.app.domain.repo.UserRepoContract

class UserRepo(private val api: SonarDataSource, private val socket: SonarSocketConnection) :
    UserRepoContract {
    override suspend fun subscribeToChannel(message: SubscribeMessage) : Boolean{
        return socket.startSubscribe(message)
    }


    @WorkerThread
    override suspend fun logUser(user: UserLogin) {
        api.logUser(user)
    }
}