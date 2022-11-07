package com.example.homeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentB2Binding

class B2Fragment : Fragment(R.layout.fragment_b2) {
    private var _binding: FragmentB2Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentB2Binding.bind(view)
        (activity as MainActivity).setActionBarTitle(B2_FRAGMENT_TAG)
        with(binding){
            tv.text = arguments?.getString("key")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val B2_FRAGMENT_TAG = "Fragment B2"
        fun getInstance() = B2Fragment()
    }
}