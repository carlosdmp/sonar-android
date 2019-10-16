package cdmp.app.data.service

import cdmp.app.domain.model.SubscribeMessage
import com.tinder.scarlet.ws.Send

interface SonarSocketService {
    @Send
    fun sendSubscribe(subscribe: SubscribeMessage) : Boolean
//
//    @Receive
//    suspend fun observeTicker(): ReceiveChannel<ChatMessage>

//    @Receive
//    suspend fun observeOnConnectionOpenedEvent(): Sequence<com.tinder.scarlet.WebSocket.Event.OnConnectionOpened<*>>
}

