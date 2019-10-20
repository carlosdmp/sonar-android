package cdmp.app.domain.repo

import cdmp.app.domain.model.MessageRequest
import cdmp.app.domain.model.UserLogin

interface UserRepoContract{
    suspend fun logUser(user: UserLogin)

    suspend fun subscribeToChannel(message: MessageRequest) : Boolean

    suspend fun getUserID() : String
}