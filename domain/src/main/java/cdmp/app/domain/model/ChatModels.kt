package cdmp.app.domain.model

data class ChatMessage(
    val senderId: String,
    val text: String
)

data class SubscribeMessage(val userId: String)