package com.kjh.umc_project8.ui.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kjh.umc_project8.databinding.ActivityMemoBinding
import com.kjh.umc_project8.database.AppDatabase
import com.kjh.umc_project8.model.Memo

class MemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoBinding
    private lateinit var roomDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomDb = AppDatabase.getInstance(this)!!

        onSaveButton()
    }

    private fun onSaveButton() {
        binding.btSave.setOnClickListener {
            if(!binding.etMemo.text.isNullOrBlank()) {
                roomDb.memoDao().insert(Memo(binding.etMemo.text.toString()))
                finish()
            }else {
                Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}