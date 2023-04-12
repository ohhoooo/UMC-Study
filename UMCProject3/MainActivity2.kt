package com.kjh.umcproject3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.kjh.umcproject3.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // MainActivity1에서 가져온 String data 를 textView 에 설정
        val value: String? = intent.getStringExtra("data")
        binding.textView.text = value

        // 버튼 클릭 시, MainActivity3로 이동
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        // 백 버튼 클릭 Callback 등록
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    // Callback
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val intent = Intent(this@MainActivity2, MainActivity::class.java).apply {
                putExtra("backString","Back")
            }
            setResult(RESULT_OK, intent)
            if(!isFinishing) finish()
        }
    }
}