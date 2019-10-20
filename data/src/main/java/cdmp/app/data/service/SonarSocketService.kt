package cdmp.app.data.service

import cdmp.app.domain.model.MessageRequest
import cdmp.app.domain.model.MessageRequestEntity
import cdmp.app.domain.model.MessageResponse
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.channels.ReceiveChannel

interface SonarSocketService {
    @Send
    fun sendMessage(subscribe: MessageRequestEntity) : Boolean

    @Receive
    fun observeChatMessages(): ReceiveChannel<MessageResponse>

//    @Receive
//    fun observeOnConnectionOpenedEvent(): Sequence<com.tinder.scarlet.WebSocket.Event.OnConnectionOpened<*>>
}

