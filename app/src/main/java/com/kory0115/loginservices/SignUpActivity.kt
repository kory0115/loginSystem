package com.kory0115.loginservices

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kory0115.loginservices.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity(){
    private val binding by lazy { ActivitySignupBinding.inflate( layoutInflater ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.openButton.setOnClickListener {
            if(binding.nameEditTextView.text.toString().isEmpty()
                || binding.idEditTextView.text.toString().isEmpty()
                || binding.passwordEditTextView.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "모든항목을 작성해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(MainActivity.MY_LOGIN_NAME, binding.nameEditTextView.text.toString())
                putExtra(MainActivity.MY_LOGIN_ID, binding.idEditTextView.text.toString())
                putExtra(MainActivity.MY_LOGIN_PASSWORD, binding.passwordEditTextView.text.toString())
            })
            finish()

            /*val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", binding.nameEditTextView.text.toString())
            intent.putExtra("id", binding.idEditTextView.text.toString())
            intent.putExtra("password", binding.passwordEditTextView.text.toString())
            startActivity(intent)*/
        }
    }
}