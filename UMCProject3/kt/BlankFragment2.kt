package com.kjh.umcproject3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kjh.umcproject3.databinding.FragmentBlank1Binding
import com.kjh.umcproject3.databinding.FragmentBlank2Binding

class BlankFragment2 : Fragment() {

    private lateinit var binding: FragmentBlank2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlank2Binding.inflate(inflater, container, false)
        return binding.root
    }
}