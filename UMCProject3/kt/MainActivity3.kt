package com.kjh.umcproject3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kjh.umcproject3.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        changedFragmentView(fragment1)

        binding.button1.setOnClickListener {
            changedFragmentView(fragment1)
        }

        binding.button2.setOnClickListener {
            changedFragmentView(fragment2)
        }

        supportFragmentManager.setFragmentResultListener("requestKey", this) { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        }
    }

    private fun changedFragmentView(fragment: Int) {
        if (fragment == 1) supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, BlankFragment1()).commit()
        else supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, BlankFragment2()).commit()
    }

    companion object {
        private const val fragment1 = 1
        private const val fragment2 = 2
    }
}