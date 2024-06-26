package com.kory0115.loginservices

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.internal.TextWatcherAdapter
import com.kory0115.loginservices.R.drawable.button_background2
import com.kory0115.loginservices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    var id: String? = ""
    var name: String? = ""
    var password: String? = ""

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if(result.resultCode != Activity.RESULT_OK) {
            return@registerForActivityResult
        }

        if(result.resultCode == Activity.RESULT_OK) {
            id = result.data?.getStringExtra(MY_LOGIN_ID)
            password = result.data?.getStringExtra(MY_LOGIN_PASSWORD)
            name = result.data?.getStringExtra(MY_LOGIN_NAME)
            binding.idEditTextView.setText(id)
        } else {
            Toast.makeText(this, "수신 실패", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindView()
        initView()
    }

    private fun initView() {
        binding.passwordEditTextView.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                if(binding.idEditTextView.length() > 0 && binding.passwordEditTextView.length() > 0) {
                    binding.loginButton.isClickable = true
                    binding.loginButton.isEnabled = true
                    binding.loginButton.setBackgroundResource(button_background2)
                } else {
                    binding.loginButton.isEnabled = false
                    binding.loginButton.isClickable = false
                }
            }
        })
        binding.idEditTextView.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter() {

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                if(binding.passwordEditTextView.length() > 0 && binding.idEditTextView.length() > 0) {
                    binding.loginButton.isClickable = true
                    binding.loginButton.isEnabled = true
                    binding.loginButton.setBackgroundResource(button_background2)
                } else {
                    binding.loginButton.isEnabled = false
                    binding.loginButton.isClickable = false
                }
            }
        })
    }


    private fun bindView() = with(binding){
        /*val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val password = intent.getStringExtra("password")*/

        loginButton.setOnClickListener {
            if(idEditTextView.text.toString() == id && passwordEditTextView.text.toString() == password) {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", name)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "아이디나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        registerButton.setOnClickListener {
            resultLauncher.launch(Intent(this@MainActivity, SignUpActivity::class.java))
        }
    }

    companion object {
        const val MY_LOGIN_NAME = "myloginkey"
        const val MY_LOGIN_ID = "myloginid"
        const val MY_LOGIN_PASSWORD = "myloginpassword"
    }
}