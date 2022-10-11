package com.example.homeworks.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentFirstBinding
import kotlin.random.Random

class FirstFragment : Fragment( R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var number: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        (activity as MainActivity).setActionBarTitle(getString(R.string.first_fragment_title))
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding){
            btnRndNumber.setOnClickListener{
                number = Random.nextInt()
            }
            btnThirdFragment.setOnClickListener{
                var fragment = ThirdFragment.getInstance()
                number?.let {
                    fragment.arguments = Bundle().apply {
                        putInt("number", it)
                    }
                }

                (requireActivity() as MainActivity)?.replaceFragment(
                    fragment,
                    SecondFragment.SECOND_FRAGMENT_TAG,
                    detachCurrent = true
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun getInstance() = FirstFragment()
    }
}

