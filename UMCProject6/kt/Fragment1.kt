package com.kjh.umc_project6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.kjh.umc_project6.databinding.Fragment1Binding

class Fragment1 : Fragment() {

    private lateinit var binding: Fragment1Binding
    private lateinit var viewPagerAdapter: Fragment1Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager 에 Adapter 연결
        viewPagerAdapter = Fragment1Adapter(this)
        binding.viewpager.adapter = viewPagerAdapter

        // ViewPager 와 TabLayout 연결
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = "TAB${position+1}"
        }.attach()
    }
}