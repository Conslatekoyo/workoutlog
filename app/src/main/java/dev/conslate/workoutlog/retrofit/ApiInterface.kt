package dev.conslate.workoutlog.retrofit
import dev.conslate.workoutlog.model.LoginRequest
import dev.conslate.workoutlog.model.LoginResponse
import dev.conslate.workoutlog.model.RegisterRequest
import dev.conslate.workoutlog.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest):Call<LoginResponse>
}