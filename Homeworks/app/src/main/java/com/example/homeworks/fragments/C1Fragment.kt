package com.example.homeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentCBinding


class C1Fragment : Fragment(R.layout.fragment_c) {
    private var _binding: FragmentCBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCBinding.bind(view)
        (activity as MainActivity).setActionBarTitle(C1_FRAGMENT_TAG)
        binding.btnNext.setOnClickListener{
                findNavController().navigate(R.id.action_c1Fragment_to_c2Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val C1_FRAGMENT_TAG = "Fragment C1"
        fun getInstance() = C1Fragment()
    }
}