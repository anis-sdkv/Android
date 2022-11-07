package com.example.homeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentABinding

class A1Fragment : Fragment(R.layout.fragment_a) {
    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentABinding.bind(view)
        (activity as MainActivity).setActionBarTitle(A1_FRAGMENT_TAG)
        initListeners()
    }

    private fun  initListeners(){
        with(binding){
            btnNext.setOnClickListener(){
                findNavController().navigate(R.id.action_a1Fragment_to_a2Fragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val A1_FRAGMENT_TAG = "Fragment A1"
        fun getInstance() = A1Fragment()
    }
}