package dev.conslate.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.conslate.workoutlog.databinding.ActivitySignupBinding
import dev.conslate.workoutlog.model.RegisterRequest
import dev.conslate.workoutlog.model.RegisterResponse
import dev.conslate.workoutlog.retrofit.ApiClient
import dev.conslate.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

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

                makeRegistrationRequest(registerRequest)
            }
        }
    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        var  request= apiClient.registerUser(registerRequest)

        request.enqueue(object :Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    var message = response.body()?.message
                    Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
                    //intent to login
                    startActivity(Intent(baseContext, LoginActivity::class.java))


                }else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })

    }


}




