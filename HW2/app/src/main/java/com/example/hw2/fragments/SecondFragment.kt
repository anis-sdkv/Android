package com.example.hw2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.hw2.MainActivity
import com.example.hw2.R
import com.example.hw2.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)
        (activity as MainActivity).setActionBarTitle(getString(R.string.second_fragment_title))
        setData()
    }

    private fun setData() {
        with(binding) {
            var counter = arguments?.getInt("counter") ?: 0
            var color = arguments?.getInt("color") ?: 0
            if (counter != 0) {
                tvConter.setText(counter.toString())
                tvConter.visibility = View.VISIBLE
            }
            colorBox.setBackgroundColor(resources.getColor(FirstFragment.colors[color], null))
        }
    }

    companion object {
        const val SECOND_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"

        fun getInstance() = SecondFragment()
    }
}