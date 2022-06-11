package dev.conslate.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var tilPassword:TextInputLayout
    lateinit var tilEmail:TextInputLayout
    lateinit var etPassword:TextInputEditText
    lateinit var etEmails:TextInputEditText
    lateinit var btnLogin:Button
    lateinit var tvSignUp:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etEmails=findViewById(R.id.etEmails)
        etPassword=findViewById(R.id.etPassword)
        btnLogin=findViewById(R.id.btnLogin)
        tvSignUp=findViewById(R.id.tvSignUp)

        btnLogin.setOnClickListener {
           val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            verify()
        }
        tvSignUp.setOnClickListener {
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
    }
    fun verify(){
        var mail=etEmails.text.toString()
        var passcode=etPassword.text.toString()
        var error=false
        if (mail.isBlank()){
            tilEmail.error="Error"
        }
        if (passcode.isBlank()){
            tilPassword.error="Error"
        }
        if (!error){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }

}
