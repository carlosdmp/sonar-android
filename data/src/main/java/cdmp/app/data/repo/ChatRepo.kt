package cdmp.app.data.repo

import cdmp.app.data.LocationService
import cdmp.app.data.SonarSocketConnection
import cdmp.app.data.datasource.SonarDataSource
import cdmp.app.domain.model.MessageRequest
import cdmp.app.domain.model.MessageResponse
import cdmp.app.domain.repo.ChatRepoContract
import kotlinx.coroutines.channels.ReceiveChannel

class ChatRepo(
    private val api: SonarDataSource,
    private val locationService: LocationService,
    private val socket: SonarSocketConnection
) : ChatRepoContract {
    override suspend fun sendMessage(message: MessageRequest): Boolean {
        return socket.sendMessage(message.copy(
            geoPos = locationService.location
        ))
    }

    override suspend fun subscribeToChatMessages() : ReceiveChannel<MessageResponse> {
        return socket.startSubscribeToChatMessage()
    }
}