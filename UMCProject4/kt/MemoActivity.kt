package com.kjh.umcproject4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.kjh.umcproject4.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        println("onCreate 실행됨")

        // editText 의 text 값을 string 으로 intent 객체에 넣어서 startActivity(intent)로 보냄
        binding.button.setOnClickListener {
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("editText", binding.editText.text.toString())
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        println("onStart 실행됨")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart 실행됨")

        // AlertDialog, 아니오 버튼 클릭 시 전역 변수 및 editText 의 Text 를 비움
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Dialog의 Title 입니다.")
            .setMessage("다시 작성하실건가요?")
            .setPositiveButton("네") { dialog, id ->

            }
            .setNegativeButton("아니오") { dialog, id ->
                GlobalVariable.globalVariable = ""
                binding.editText.setText(GlobalVariable.globalVariable)
            }
        builder.show()
    }

    override fun onResume() {
        super.onResume()
        println("onResume 실행됨")

        // 전역 변수가 Null 또는 Blank 가 아닐 경우에만 editText 의 Text 설정
        if(!GlobalVariable.globalVariable.isNullOrBlank()) {
            binding.editText.setText(GlobalVariable.globalVariable)
        }
    }

    override fun onPause() {
        super.onPause()
        println("onPause 실행됨")

        // onPause() 콜백이 호출될 때 editText의 text 값을 string 형태로 전역 변수에 저장
        GlobalVariable.globalVariable = binding.editText.text.toString()
    }

    override fun onStop() {
        super.onStop()
        println("onStop 실행됨")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy 실행됨")
    }

    // Activity LifeCycle 의 각 단계를 Log 로 출력
    private fun println(lifeCycle: String) {
        Log.d("라이프사이클1", lifeCycle)
    }
}