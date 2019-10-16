package cdmp.app.data

import androidx.annotation.WorkerThread
import cdmp.app.data.service.SonarSocketService
import cdmp.app.domain.model.SubscribeMessage
import cdmp.app.domain.model.User


class SonarSocketConnection(private val service: SonarSocketService) {

    @WorkerThread
    suspend fun startSubscribe(message: SubscribeMessage): Boolean {
        return service.sendSubscribe(message)
    }

}

