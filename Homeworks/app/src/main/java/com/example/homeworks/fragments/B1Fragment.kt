package com.example.homeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentBBinding

class B1Fragment : Fragment(R.layout.fragment_b) {
    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBBinding.bind(view)
        (activity as MainActivity).setActionBarTitle(B1_FRAGMENT_TAG)
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            btnNext .setOnClickListener() {
                findNavController().navigate(R.id.action_b1Fragment_to_b2Fragment, bundleOf(
                    "key" to et.text.toString()
                ))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val B1_FRAGMENT_TAG = "Fragment B1"
        fun getInstance() = B1Fragment()
    }
}