package cdmp.app.domain.model

import java.util.*

data class MessageRequest(
    val userId: String,
    val message: String,
    val geoPos: UserLocation?,
    val createdAt: Date
)

data class MessageRequestEntity(
    val userId: String,
    val message: String,
    val geoPos: UserLocation?,
    val createdAt: String
)

data class MessageResponse(
    val id: String,
    val userId: String,
    val message: String,
    val geoPos: UserLocation?,
    val createdAt: String
)


data class UserLocation(
    val latitude: Double,
    val longitude: Double
)