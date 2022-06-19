package dev.conslate.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.conslate.workoutlog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
           val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            verify()
        }
        binding.tvSignUp.setOnClickListener {
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
    }
    fun verify(){


        var mail=binding.etEmails.text.toString()
        var passcode=binding.etPassword.text.toString()
        var error=false
        if (mail.isBlank()){
            binding.tilEmail.error="Error"
        }
        if (passcode.isBlank()){
            binding.tilPassword.error="Error"
        }
        if (!error){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }
}
