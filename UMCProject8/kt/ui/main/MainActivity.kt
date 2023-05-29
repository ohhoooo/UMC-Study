package com.kjh.umc_project8.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kjh.umc_project8.databinding.ActivityMainBinding
import com.kjh.umc_project8.database.AppDatabase
import com.kjh.umc_project8.ui.SharedAdapter
import com.kjh.umc_project8.ui.memo.MemoActivity
import com.kjh.umc_project8.ui.storage.StorageActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: SharedAdapter
    private lateinit var roomDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setRoom()
        setMemoButton()
        setStorageButton()
    }

    private fun setAdapter() {
        mainAdapter = SharedAdapter(this)
        binding.recyclerView.adapter = mainAdapter
    }

    private fun setRoom() {
        roomDb = AppDatabase.getInstance(this)!!
        val memoList = roomDb.memoDao().selectAll()
        mainAdapter.submitList(memoList)
    }

    private fun setMemoButton() {
        binding.btMemo.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setStorageButton() {
        binding.btStorage.setOnClickListener {
            val intent = Intent(this, StorageActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // MainActivity 가 재개될 때 마다 DB 에서 데이터를 가져온다.
        val memoList = roomDb.memoDao().selectAll()
        mainAdapter.submitList(memoList)
    }
}