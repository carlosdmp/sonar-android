package cdmp.app.data

import androidx.annotation.WorkerThread
import cdmp.app.data.service.SonarSocketService
import cdmp.app.domain.model.MessageRequest
import cdmp.app.domain.model.MessageRequestEntity
import cdmp.app.domain.model.MessageResponse
import kotlinx.coroutines.channels.ReceiveChannel


class SonarSocketConnection(private val service: SonarSocketService) {

    @WorkerThread
    suspend fun sendMessage(message: MessageRequest): Boolean {
        return service.sendMessage(
            MessageRequestEntity(
                userId = message.userId,
                createdAt = message.createdAt.toString(),
                geoPos = message.geoPos,
                message = message.message
            )
        )
    }

    @WorkerThread
    suspend fun startSubscribeToChatMessage(): ReceiveChannel<MessageResponse> {
        return service.observeChatMessages()
    }

}

