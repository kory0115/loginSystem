package com.kory0115.loginservices

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kory0115.loginservices.databinding.ActivityHomeBinding
import java.util.*

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate( layoutInflater ) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        mbtiMaker()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() = with(binding) {
        val random = Random()
        val mbti = mbtiMaker()

        when(random.nextInt(4)) {
            0 -> titleImageView.setImageResource(R.drawable.image1)
            1 -> titleImageView.setImageResource(R.drawable.image2)
            2 -> titleImageView.setImageResource(R.drawable.image3)
            3 -> titleImageView.setImageResource(R.drawable.image4)
            4 -> titleImageView.setImageResource(R.drawable.image5)
        }
        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        idTextView.text = "아이디:$id"
        nameTextView.text = "이름:$name"
        ageTextView.text = "나이:${random.nextInt(50) + 1}"
        mbtiTextView.text = "mbti:${mbti}"
        runOnUiThread {
            middleLayout.animate()
                .alpha(1.0f)
                .setDuration(1000)
                .start()
        }
        closeButton.setOnClickListener {
            finish()
        }
    }

    private fun mbtiMaker() : String {
        val random = Random()
        val arr = arrayOf<Pair<String, String>>(Pair("E", "I"), Pair("S", "N"), Pair("T", "F"), Pair("J", "P"))
        var result = ""
        for(i in arr.indices) {
            when(random.nextInt(1)) {
                0 -> result += arr[i].first
                1 -> result += arr[i].second
            }
        }
        return result
    }
}
