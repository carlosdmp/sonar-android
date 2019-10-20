package cdmp.app.domain.case

import cdmp.app.domain.model.MessageResponse
import cdmp.app.domain.repo.ChatRepoContract
import cdmp.app.domain.repo.UserRepoContract
import com.cdmp.rickmorty.domain.datatype.Either
import com.cdmp.rickmorty.domain.datatype.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.withContext

typealias ObserveMessageResult = Either<Throwable, ReceiveChannel<MessageResponse>>

class ObserveMessagesCase(
    private val userRepo: UserRepoContract,
    private val chatRepo: ChatRepoContract,
    private val io: CoroutineDispatcher
) {

    suspend fun getMessages(): ObserveMessageResult =
        withContext(io) {
            safeCall(errorMapper = { it }) {
                chatRepo.subscribeToChatMessages()
            }
        }
}
