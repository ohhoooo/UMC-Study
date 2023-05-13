package com.kjh.umc_project6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kjh.umc_project6.databinding.Fragment2Binding

class Fragment2 : Fragment() {

    private lateinit var binding: Fragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager 에 Adapter 연결
        binding.viewpager.adapter = Fragment2Adapter()

        // Indicator 에 ViewPager 연결
        binding.indicator.setViewPager(binding.viewpager)
    }
}