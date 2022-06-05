package dev.conslate.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {
    lateinit var tilFirst:TextInputLayout
    lateinit var tilLast: TextInputLayout
    lateinit var tilmail: TextInputLayout
    lateinit var tilPass: TextInputLayout
    lateinit var tilconfirm: TextInputLayout
    lateinit var btnWakilisha:Button
    lateinit var etFirst:TextInputEditText
    lateinit var etLast:TextInputEditText
    lateinit var etEmails:TextInputEditText
    lateinit var etPasswords:TextInputEditText
    lateinit var etGmail:TextInputEditText
    lateinit var tvLog: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        tilFirst=findViewById(R.id.tilFirst)
        tilLast=findViewById(R.id.tilLast)
        tilmail=findViewById(R.id.tilmail)
        tilPass=findViewById(R.id.tilPass)
        tilconfirm=findViewById(R.id.tilconfirm)
        etFirst=findViewById(R.id.etFirst)
        etPasswords=findViewById(R.id.etPasswords)
        etGmail=findViewById(R.id.etGmail)
        etLast=findViewById(R.id.etLast)
        tvLog=findViewById(R.id.tvLog)
        etEmails=findViewById(R.id.etEmails)
        btnWakilisha = findViewById(R.id.btnWakilisha)

        btnWakilisha.setOnClickListener {
            cons()
        }
        tvLog.setOnClickListener {
           val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun cons(){
        var gmail=etGmail.text.toString()
        var pass=etPasswords.text.toString()
        var first=etFirst.text.toString()
        var last=etLast.text.toString()
        var mailer=etEmails.text.toString()

        if (gmail.isBlank()){
            tilmail.error="Error"
        }
        if (pass.isBlank()){
            tilPass.error="Error"
        }
        if (first.isBlank()){
            tilFirst.error="Error"
        }
        if (last.isBlank()){
            tilLast.error="Error"
        }
        if (mailer.isBlank()){
            tilconfirm.error="Error"
        }
    }
}