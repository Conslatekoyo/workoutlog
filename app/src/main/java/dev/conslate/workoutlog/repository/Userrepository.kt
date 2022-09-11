package dev.conslate.workoutlog.repository

import dev.conslate.workoutlog.api.ApiClient
import dev.conslate.workoutlog.api.ApiInterface
import dev.conslate.workoutlog.model.LoginRequest
import dev.conslate.workoutlog.model.RegisterRequest
import dev.conslate.workoutlog.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Userrepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest) = withContext(Dispatchers.IO) {
        val response = apiClient.login(loginRequest)
        return@withContext response
    }

    suspend fun registerUser(registerRequest: RegisterRequest) =
        withContext(Dispatchers.IO) {
            val response = apiClient.registerUser(registerRequest)
            return@withContext response
        }

}