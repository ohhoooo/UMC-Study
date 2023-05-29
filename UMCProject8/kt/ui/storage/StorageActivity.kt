package com.kjh.umc_project8.ui.storage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kjh.umc_project8.databinding.ActivityStorageBinding
import com.kjh.umc_project8.database.AppDatabase
import com.kjh.umc_project8.model.Memo
import com.kjh.umc_project8.ui.SharedAdapter

class StorageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStorageBinding
    private lateinit var roomDb: AppDatabase
    private lateinit var storageAdapter: SharedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setRoom()
        setTerminationButton()
    }

    private fun setAdapter() {
        storageAdapter = SharedAdapter(this)
        binding.recyclerView.adapter = storageAdapter
    }

    private fun setRoom() {
        roomDb = AppDatabase.getInstance(this)!!

        val sharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val memoList = mutableListOf<Memo>()
        sharedPreferences.all.values.forEach {
            val memo = roomDb.memoDao().selectByMemoId(it as Int)
            memoList.add(memo)
        }
        storageAdapter.submitList(memoList)
    }

    private fun setTerminationButton() {
        binding.btTermination.setOnClickListener {
            finish()
        }
    }
}