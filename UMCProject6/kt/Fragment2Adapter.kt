package com.kjh.umc_project6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kjh.umc_project6.databinding.ItemCircleIndicatorBinding

class Fragment2Adapter : RecyclerView.Adapter<Fragment2Adapter.ViewHolder>(){

    private lateinit var binding: ItemCircleIndicatorBinding
    private val images = arrayListOf(
        R.drawable.color1,
        R.drawable.color2,
        R.drawable.color3,
        R.drawable.color4
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCircleIndicatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(private val binding: ItemCircleIndicatorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.image.setImageResource(image)
        }
    }
}