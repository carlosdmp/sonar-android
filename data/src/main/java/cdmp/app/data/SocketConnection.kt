package cdmp.app.data

import androidx.annotation.WorkerThread
import cdmp.app.data.service.SonarSocketService


class SonarSocketConnection(private val service: SonarSocketService) {

    @WorkerThread
    suspend fun startSubscribe() : Boolean{
        return service.sendSubscribe("A ver si va")
    }

}