package com.kjh.umc_project5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.kjh.umc_project5.databinding.RecyclerviewItemBinding

class RecyclerViewAdapter(val context: Context, val getResult: ActivityResultLauncher<Intent>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // 리사이클러뷰에 표시 할 데이터 초기 설정
    private val dataSet: MutableList<Item> = mutableListOf<Item>().apply {
        for(i in 0..30) {
            add(Item("$i"))
        }
    }

    // 데이터 삭제
    private fun removeData(position: Int) {
        dataSet.removeAt(position) // 1. 데이터를 지우고
        notifyItemRemoved(position) // 2. 어덥터에 알려주면 화면을 갱신한다.
    }

    // 데이터 추가
    fun addData(item: Item) {
        dataSet.add(item) // 1. 데이터를 추가하고
        notifyItemInserted(dataSet.size-1) // 2. 어덥터에 알려주면 화면을 갱신한다.
    }

    // 데이터 수정
    fun editData(item: Item, position: Int) {
        dataSet[position] = item // 1. 데이터를 수정하고
        notifyItemChanged(position) // 2. 어덥터에 알려주면 화면을 갱신한다.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(private val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Item) {
            with(binding) {
                // 데이터 바인딩
                tvNoteContent.text = data.content
                switch1.isChecked = data.isChecked

                // 클릭 시 체크 상태를 바꾼다.
                switch1.setOnClickListener {
                    data.isChecked = binding.switch1.isChecked
                    notifyItemChanged(adapterPosition)
                }

                // 클릭하면 해당 layout 의 position 과 context 를 intent 에 넣고 getResult 를 실행한다. -> 나중에 편집 된 text 의 결과를 getResult 에 전달
                container.setOnClickListener {
                    val intent = Intent(context, MemoActivity::class.java).apply {
                        putExtra("layoutPosition", layoutPosition) // layoutPosition 은 ViewHolder 클래스의 내장 함수
                        putExtra("context", dataSet[layoutPosition].content)
                    }
                    getResult.launch(intent)
                }

                // 길게 클릭하면 해당 layout 의 데이터를 삭제한다.
                container.setOnLongClickListener {
                    removeData(adapterPosition)
                    return@setOnLongClickListener true
                }
            }
        }
    }
}