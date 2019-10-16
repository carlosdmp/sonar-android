package cdmp.app.data.service

import cdmp.app.domain.model.User
import cdmp.app.domain.model.UserLogin
import retrofit2.http.Body
import retrofit2.http.POST

const val BaseUrl = "http://10.0.2.2:8888"

interface SonarService {
    @POST("user")
    suspend fun logUser(@Body user: UserLogin): Unit
}