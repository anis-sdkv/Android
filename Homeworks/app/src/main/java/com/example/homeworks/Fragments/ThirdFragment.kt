package com.example.homeworks.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private val backgroundColors = listOf<Int>(R.color.green, R.color.purple, R.color.red)
    private var currentColor = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentThirdBinding.bind(view)
        (activity as MainActivity).setActionBarTitle(getString(R.string.third_fragment_title))
        initLayout()
    }

    fun initLayout() {
        var number = arguments?.getInt("number")
        number?.let {
            binding.tvThirdFragment.setText(it.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        changeBackgroundColor()
    }

    fun changeBackgroundColor() {
        if (++currentColor == backgroundColors.size)
            currentColor = 0
        binding.root.setBackgroundColor(requireContext().getColor(backgroundColors[currentColor]))
    }

    companion object {
        const val THIRD_FRAGMENT_TAG = "THIRD_FRAGMENT_TAG"
        fun getInstance() = ThirdFragment()
    }
}