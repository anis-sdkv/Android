package com.example.homeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentC3Binding

class C3Fragment : Fragment(R.layout.fragment_c3) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(activity as MainActivity) {
            setActionBarTitle(C3_FRAGMENT_TAG)
        }
    }

    companion object {
        const val C3_FRAGMENT_TAG = "Fragment C3"
        fun getInstance() = C3Fragment()
    }
}