package com.example.homeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentA2Binding

class A2Fragment : Fragment(R.layout.fragment_a2) {
    private var _binding: FragmentA2Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentA2Binding.bind(view)
        (activity as MainActivity).setActionBarTitle(A2_FRAGMENT_TAG)
        initListeners()
    }

    private fun  initListeners(){
        with(binding){
            btnNext.setOnClickListener(){
                findNavController().navigate(R.id.action_a2Fragment_to_a3Fragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val A2_FRAGMENT_TAG = "Fragment A2"
        fun getInstance() = A2Fragment()
    }
}