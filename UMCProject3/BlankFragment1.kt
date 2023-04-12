package com.kjh.umcproject3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.kjh.umcproject3.databinding.FragmentBlank1Binding

class BlankFragment1 : Fragment() {

    private lateinit var binding: FragmentBlank1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlank1Binding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            parentFragmentManager.setFragmentResult("requestKey", bundleOf("bundleKey" to binding.editText.text.toString()))
        }

        return binding.root
    }
}