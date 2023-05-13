package com.kjh.umc_project6

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class Fragment1Adapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ViewPagerFragment1()
            1 -> ViewPagerFragment2()
            else -> ViewPagerFragment3()
        }
    }
}