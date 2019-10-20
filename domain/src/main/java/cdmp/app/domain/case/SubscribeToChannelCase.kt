package cdmp.app.domain.case

import cdmp.app.domain.model.MessageRequest
import cdmp.app.domain.repo.UserRepoContract
import com.cdmp.rickmorty.domain.datatype.Either
import com.cdmp.rickmorty.domain.datatype.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

typealias SubscribeResult = Either<Throwable, Boolean>

class SubscribeToChannelCase(private val repo: UserRepoContract, private val io: CoroutineDispatcher) {

    suspend fun subscribeToChannel(message: MessageRequest): SubscribeResult =
        withContext(io) {
            safeCall(errorMapper = { it }) {
                repo.subscribeToChannel(message)
            }
        }
}
