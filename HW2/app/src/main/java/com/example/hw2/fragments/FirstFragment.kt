package com.example.hw2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.hw2.MainActivity
import com.example.hw2.R
import com.example.hw2.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var counter: Int = 0
    private var currColor: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        (activity as MainActivity).setActionBarTitle(getString(R.string.first_fragment_title))
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            btnSecondFragment.setOnClickListener {
                var fragment = SecondFragment.getInstance()
                fragment.arguments = Bundle().apply {
                    putInt("counter", counter)
                    putInt("color", currColor)
                }

                (requireActivity() as MainActivity)?.addFragment(
                    fragment,
                    SecondFragment.SECOND_FRAGMENT_TAG,
                    detachCurrent = true
                )
            }

            btnCounter.setOnClickListener {
                counter++
            }

            btnColor.setOnClickListener {
                currColor++
                if (currColor >= colors.size)
                    currColor = 0;
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun getInstance() = FirstFragment()

        val colors: Array<Int> =
            arrayOf(R.color.red, R.color.green, R.color.beige, R.color.purple)
    }
}