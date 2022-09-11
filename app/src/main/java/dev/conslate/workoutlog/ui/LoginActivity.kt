package dev.conslate.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.conslate.workoutlog.databinding.ActivityLoginBinding
import dev.conslate.workoutlog.model.LoginRequest
import dev.conslate.workoutlog.model.LoginResponse
import dev.conslate.workoutlog.api.ApiClient
import dev.conslate.workoutlog.api.ApiInterface
import dev.conslate.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("WORKOUTLOG PREFS", MODE_PRIVATE)

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

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse ->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        })

        userViewModel.loginErrorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

        })

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


    }
    fun saveLoginDetails(LoginResponse: LoginResponse) {
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", LoginResponse.accessToken)
        editor.putString("USER_ID", LoginResponse.userId)
        editor.putString("PROFILE_ID", LoginResponse.profileId)
        editor.apply()
    }
}


