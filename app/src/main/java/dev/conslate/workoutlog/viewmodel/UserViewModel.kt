package dev.conslate.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.conslate.workoutlog.model.LoginRequest
import dev.conslate.workoutlog.model.LoginResponse
import dev.conslate.workoutlog.model.RegisterRequest
import dev.conslate.workoutlog.model.RegisterResponse
import dev.conslate.workoutlog.repository.Userrepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository=Userrepository()
    var loginResponseLiveData=MutableLiveData<LoginResponse>()
    val loginErrorLiveData=MutableLiveData<String?>()
    var registerResponseLiveData=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData=MutableLiveData<String?>()


    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response=userRepository.loginUser(loginRequest)
            if(response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                val error=response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }

        }
    }

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequest)
            if(response.isSuccessful){
                registerResponseLiveData.postValue(response.body())
            }
        }
    }

}