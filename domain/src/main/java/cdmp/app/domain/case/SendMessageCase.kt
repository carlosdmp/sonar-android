package cdmp.app.domain.case

import cdmp.app.domain.model.MessageRequest
import cdmp.app.domain.repo.ChatRepoContract
import cdmp.app.domain.repo.UserRepoContract
import com.cdmp.rickmorty.domain.datatype.Either
import com.cdmp.rickmorty.domain.datatype.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.*

typealias SendMessageResult = Either<Throwable, Boolean>

class SendMessageCase(
    private val userRepo: UserRepoContract,
    private val chatRepo: ChatRepoContract,
    private val io: CoroutineDispatcher
) {

    suspend fun sendMessage(messageText: String): SendMessageResult =
        withContext(io) {
            safeCall(errorMapper = { it }) {
                val message = MessageRequest(
                    userId = userRepo.getUserID(),
                    geoPos = null,
                    message = messageText,
                    createdAt = Calendar.getInstance().time
                )
                chatRepo.sendMessage(message)
            }
        }
}
