package com.example.homeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentC2Binding

class C2Fragment : Fragment(R.layout.fragment_c2) {
    private var _binding: FragmentC2Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentC2Binding.bind(view)
        (activity as MainActivity).setActionBarTitle(C2_FRAGMENT_TAG)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_c2Fragment_to_c3Fragment)
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).changeBottomNavVisibility(View.INVISIBLE)
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).changeBottomNavVisibility(View.VISIBLE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val C2_FRAGMENT_TAG = "Fragment C2"
        fun getInstance() = C2Fragment()
    }
}