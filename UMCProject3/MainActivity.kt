package com.kjh.umcproject3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.kjh.umcproject3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // button 을 누를 시 intent 에 editText 의 정보를 string 형태로 담아 MainActivity2 로 이동
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra("data", binding.editText.text.toString())
            getResultText.launch(intent)
        }

        /*
            1. registerForActivityResult 함수를 사용해서 Callback 을 등록
            2. 인자로 들어가는 것은 ActivityResultContracts 클래스의 Static 함수들
                2-1. Result 를 받기 위해 Activity 를 실행하는 StartActivityForResult() 함수
                2-2. 람다 식으로 result 로 받아온 값을 어떻게 사용하는지 정의
                    - result 객체를 이용해 resultCode 와 data 에 접근 할 수 있음
        */
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                val str = result.data?.getStringExtra("backString")
                Toast.makeText(this@MainActivity, str, Toast.LENGTH_LONG).show()
            }
        }
    }
}