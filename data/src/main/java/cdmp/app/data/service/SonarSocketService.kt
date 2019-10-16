package cdmp.app.data.service

import com.tinder.scarlet.ws.Send

interface SonarSocketService {
    @Send
    fun sendSubscribe(subscribe: String) : Boolean
//
//    @Receive
//    suspend fun observeTicker(): ReceiveChannel<ChatMessage>

//    @Receive
//    suspend fun observeOnConnectionOpenedEvent(): Sequence<com.tinder.scarlet.WebSocket.Event.OnConnectionOpened<*>>
}

