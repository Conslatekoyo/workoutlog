package dev.conslate.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.conslate.workoutlog.databinding.ActivityLoginBinding
import dev.conslate.workoutlog.model.LoginRequest
import dev.conslate.workoutlog.model.LoginResponse
import dev.conslate.workoutlog.retrofit.ApiClient
import dev.conslate.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs=getSharedPreferences("WORKOUTLOG PREFS", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            validateLogin()
        }
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateLogin() {
        var mail = binding.etEmails.text.toString()
        var passcode = binding.etPassword.text.toString()
        var error = false





        if (mail.isBlank()) {
            binding.tilEmail.error = "Error"
            error = false
        }
        if (passcode.isBlank()) {
            binding.tilPassword.error = "Error"
            error = true
        }
        if (!error) {
            var loginRequest = LoginRequest(mail, passcode)
            binding.pblogin.visibility = View.GONE


        }
        fun makeLoginRequest(loginRequest: LoginRequest) {
            var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
            val request = apiClient.login(loginRequest)

            request.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        var message = response.body()?.message
                        Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()

                    } else {
                        val error = response.errorBody()?.string()
                        Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    binding.pblogin.visibility=View.GONE
                    Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()

                }


            })
        }
        fun saveLoginDetails(LoginResponse:LoginResponse){
            val editor= sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN", LoginResponse.accessToken)
            editor.putString("USER_ID",LoginResponse.userId)
            editor.putString("PROFILE_ID",LoginResponse.profileId)
            editor.apply()
        }

    }
}


