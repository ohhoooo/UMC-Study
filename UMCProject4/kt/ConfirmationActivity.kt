package com.kjh.umcproject4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kjh.umcproject4.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        println("onCreate 실행됨")

        // intent 객체로 가져온 string data 를 text 로 설정
        binding.text.text = intent.getStringExtra("editText")
    }

    override fun onStart() {
        super.onStart()
        println("onStart 실행됨")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart 실행됨")
    }

    override fun onResume() {
        super.onResume()
        println("onResume 실행됨")
    }

    override fun onPause() {
        super.onPause()
        println("onPause 실행됨")
    }

    override fun onStop() {
        super.onStop()
        println("onStop 실행됨")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy 실행됨")
    }

    private fun println(lifeCycle: String) {
        Log.d("라이프사이클2", lifeCycle)
    }
}