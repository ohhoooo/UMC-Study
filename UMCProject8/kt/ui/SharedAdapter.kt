package com.kjh.umc_project8.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kjh.umc_project8.databinding.ItemMemoBinding
import com.kjh.umc_project8.database.AppDatabase
import com.kjh.umc_project8.model.Memo

class SharedAdapter(private val context: Context) : ListAdapter<Memo, SharedAdapter.ViewHolder>(
    MemoDiffCallback()
) {

    private val roomDb = AppDatabase.getInstance(context)
    private val sharedPreferences = context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMemoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            with(binding) {
                // 바인딩
                tvContents.text = memo.content
                switchLike.isChecked = memo.like
                switchBookmark.isChecked = memo.bookmark

                // 좋아요 버튼 클릭 이벤트
                switchLike.setOnClickListener {
                    roomDb!!.memoDao().update(
                        Memo(memo.content, switchBookmark.isChecked, switchLike.isChecked, memo.memoId)
                    )
                }

                // 북마크 버튼 클릭 이벤트
                switchBookmark.setOnClickListener {
                    // DB 의 내용을 업데이트
                    roomDb!!.memoDao().update(
                        Memo(memo.content, switchBookmark.isChecked, switchLike.isChecked, memo.memoId)
                    )

                    // switch 의 상태에 따라 sharedPreferences 에 값 저장 유무를 결정
                    if(switchBookmark.isChecked) {
                        editor.putInt(memo.memoId.toString(), memo.memoId)
                        editor.apply()
                    }else {
                        editor.remove(memo.memoId.toString())
                        editor.apply()
                    }
                }

                // Memo 를 길게 누를 시 삭제 이벤트
                container.setOnLongClickListener {
                    // sharedPreferences 에서 삭제
                    editor.remove(memo.memoId.toString())
                    editor.apply()

                    // room 에서 삭제
                    val memoDao = roomDb!!.memoDao()
                    memoDao.delete(memo)
                    submitList(memoDao.selectAll())
                    return@setOnLongClickListener true
                }
            }
        }
    }
}

class MemoDiffCallback : DiffUtil.ItemCallback<Memo>() {
    override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem.memoId == newItem.memoId
    }

    override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
        return oldItem == newItem
    }
}