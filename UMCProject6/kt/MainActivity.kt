package com.kjh.umc_project6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kjh.umc_project6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 각 Item 을 클릭했을 때 나타나는 이벤트 설정
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.camera -> changeFragment(Fragment1())
                R.id.home -> changeFragment(Fragment2())
                else -> changeFragment(Fragment3())
            }
            true
        }

        // 가장 처음 표시할 Fragment 설정
        binding.bottomNavi.selectedItemId = R.id.home
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}