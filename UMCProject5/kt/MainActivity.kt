package com.kjh.umc_project5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kjh.umc_project5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            1. registerForActivityResult 함수를 사용해서 Callback 을 등록
            2. 인자로 들어가는 것은 ActivityResultContracts 클래스의 Static 함수들
                2-1. Result 를 받기 위해 Activity 를 실행하는 StartActivityForResult() 함수
                2-2. 람다 식으로 result 로 받아온 값을 어떻게 사용하는지 정의
                    - result 객체를 이용해 resultCode 와 data 에 접근 할 수 있음
        */
        val getResultText: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                val item = Item(result.data?.getStringExtra("memoText")!!, false)
                if(result.data?.getIntExtra("position", -1) != -1) { // layout position 이 넘어왔으면 메모가 수정 된 것으로 인식
                    val position = result.data!!.getIntExtra("position", -1)
                    rvAdapter.editData(item, position)
                }else { // layout position 이 넘어오지 않았으면 새로운 메모가 등록 된 것으로 인식
                    rvAdapter.addData(item)
                }
            }
        }

        rvAdapter = RecyclerViewAdapter(this@MainActivity, getResultText) // 수정기능 구현을 위해 getResultText 를 같이 넘겨준다.

        with(binding) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = rvAdapter
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
            button.setOnClickListener {
                val intent = Intent(applicationContext, MemoActivity::class.java)
                getResultText.launch(intent)
            }
        }
    }
}