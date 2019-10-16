package cdmp.app.domain.case

import cdmp.app.domain.repo.UserRepoContract
import cdmp.app.domain.model.User
import cdmp.app.domain.model.UserLogin
import com.cdmp.rickmorty.domain.datatype.Either
import com.cdmp.rickmorty.domain.datatype.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

typealias LogUserResult = Either<Throwable, Unit>

class LogUserCase(private val repo: UserRepoContract, private val io: CoroutineDispatcher) {

    suspend fun logUser(user: UserLogin): LogUserResult =
        withContext(io) {
            safeCall(errorMapper = { it }) {
                repo.logUser(user)
            }
        }
}
