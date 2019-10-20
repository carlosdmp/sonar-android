package cdmp.app.domain.repo

import cdmp.app.domain.model.MessageRequest
import cdmp.app.domain.model.MessageResponse
import kotlinx.coroutines.channels.ReceiveChannel

interface ChatRepoContract{
    suspend fun sendMessage(message: MessageRequest) : Boolean
    suspend fun subscribeToChatMessages(): ReceiveChannel<MessageResponse>
}