package dev.conslate.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.conslate.workoutlog.databinding.ActivitySignupBinding
import dev.conslate.workoutlog.model.RegisterRequest
import dev.conslate.workoutlog.model.RegisterResponse
import dev.conslate.workoutlog.api.ApiClient
import dev.conslate.workoutlog.api.ApiInterface
import dev.conslate.workoutlog.model.LoginResponse
import dev.conslate.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnWakilisha.setOnClickListener {
            cons()
        }
        binding.tvLog.setOnClickListener {
           val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun cons(){
        var gmail=binding.etGmail.text.toString()
        var pass=binding.etPasswords.text.toString()
        var first=binding.etFirst.text.toString()
        var last=binding.etLast.text.toString()
        var mailer=binding.etEmails.text.toString()
        var error=false

        if (gmail.isBlank()){
            binding.tilmail.error="Error"
        }
        if (pass.isBlank()){
            binding.tilPass.error="Error"
        }
        if (first.isBlank()){
            binding.tilFirst.error="Error"
        }
        if (last.isBlank()){
            binding.tilLast.error="Error"
        }
        if (mailer.isBlank()){
            binding.tilconfirm.error="Error"
        }
        if(pass.equals(pass)){
            error=true
            binding.tilconfirm.error="Passwords don't match"
        }

            if(!error){
                val  registerRequest=RegisterRequest(first,last , mailer, pass,gmail)
            }
        }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData .observe(this, Observer { registerResponse->
            Toast.makeText(baseContext,registerResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,LoginActivity::class.java))
            finish()

        })
        userViewModel.registerErrorLiveData.observe(this, Observer { registerError ->
            Toast.makeText(baseContext, registerError, Toast.LENGTH_LONG).show()

        })

    }


}









