package cdmp.app.domain.repo

import cdmp.app.domain.model.User
import cdmp.app.domain.model.UserLogin

interface UserRepoContract{
    suspend fun logUser(user: UserLogin)

    suspend fun subscribeToChannel() : Boolean
}