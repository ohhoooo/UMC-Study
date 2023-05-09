package com.kjh.umc_project5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kjh.umc_project5.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            editText.setText(intent.getStringExtra("context")) // container.setOnClickListener 로 넘어왔을 경우 수정해야 할 text 를 editText 에 미리 적용

            button2.setOnClickListener {
                val intent = Intent(applicationContext, MainActivity::class.java).apply {
                    // (1) "메모화면 이동" 버튼을 통해 넘어왔으면 -1
                    // (2) container.setOnClickListener 로 넘어왔으면 해당 layoutPosition
                    putExtra("position", intent.getIntExtra("layoutPosition", -1))
                    putExtra("memoText",editText.text.toString()) // 입력되거나 수정 된 text
                }
                setResult(RESULT_OK, intent)
                if(!isFinishing) finish()
            }
        }
    }
}