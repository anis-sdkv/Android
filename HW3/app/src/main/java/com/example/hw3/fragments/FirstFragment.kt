package com.example.hw3.fragments

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hw3.MainActivity
import com.example.hw3.R
import com.example.hw3.databinding.FragmentFirstBinding
import com.example.hw3.tools.FreeFieldTextWatcher
import com.example.hw3.tools.NumberFieldTextWatcher


class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        (activity as MainActivity).setActionBarTitle(getString(R.string.first_fragment_title))
        initTextChangeListeners()
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            etNumber.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus && etNumber.text.toString() == "") {
                    etNumber.text = Editable.Factory.getInstance().newEditable("+7(9")
                }
            }

            btnSecondFragment.setOnClickListener {
                var fragment = SecondFragment.getInstance()

                (requireActivity() as MainActivity)?.addFragment(
                    fragment,
                    SecondFragment.SECOND_FRAGMENT_TAG,
                    detachCurrent = true
                )
            }
        }
    }

    private fun initTextChangeListeners() {
        with(binding) {
            etNumber.addTextChangedListener(NumberFieldTextWatcher(etNumber, etFree))
            etFree.addTextChangedListener(FreeFieldTextWatcher(etFree, btnSecondFragment))
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

