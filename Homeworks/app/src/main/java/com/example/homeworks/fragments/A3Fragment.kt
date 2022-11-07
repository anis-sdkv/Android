package com.example.homeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentA3Binding

class A3Fragment : Fragment(R.layout.fragment_a3) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setActionBarTitle(A3_FRAGMENT_TAG)
    }

    companion object {
        const val A3_FRAGMENT_TAG = "Fragment A3"
        fun getInstance() = A3Fragment()
    }
}