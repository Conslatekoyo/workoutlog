package dev.conslate.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.conslate.workoutlog.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnWakilisha.setOnClickListener {
            cons()
        }
        binding.tvLog.setOnClickListener {
           val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun cons(){
        var gmail=binding.etGmail.text.toString()
        var pass=binding.etPasswords.text.toString()
        var first=binding.etFirst.text.toString()
        var last=binding.etLast.text.toString()
        var mailer=binding.etEmails.text.toString()

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
    }
}