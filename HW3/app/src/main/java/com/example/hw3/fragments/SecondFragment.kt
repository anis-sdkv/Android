package com.example.hw3.fragments

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import com.example.hw3.R
import androidx.fragment.app.Fragment
import com.example.hw3.MainActivity
import com.example.hw3.databinding.FragmentSecondBinding
import com.example.hw3.models.ViewDimensionsModel


class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)
        (activity as MainActivity).setActionBarTitle(getString(R.string.second_fragment_title))
        var sizes = calculateSizes()
        changeViewsParams(sizes)
    }

    private fun calculateSizes(): ViewDimensionsModel {
        var vertical = requireContext().resources.displayMetrics.heightPixels / 2
        var horizontal = requireContext().resources.displayMetrics.widthPixels / 2
        return ViewDimensionsModel(horizontal, vertical)
    }

    private fun changeViewsParams(sizes: ViewDimensionsModel) {
        with(binding) {
            blocksParent.forEach {
                it.layoutParams?.apply {
                    height = sizes.verticalSize
                    width = sizes.horizontalSize
                }
            }


            with(block1.layoutParams as ConstraintLayout.LayoutParams) {
                leftToLeft = blocksParent.id
                endToEnd = ConstraintLayout.LayoutParams.UNSET
            }
            with(block2.layoutParams as ConstraintLayout.LayoutParams) {
                topToTop = blocksParent.id
                startToStart = ConstraintLayout.LayoutParams.UNSET
            }
            with(block3.layoutParams as ConstraintLayout.LayoutParams) {
                topToBottom = block1.id
                endToEnd = ConstraintLayout.LayoutParams.UNSET
            }
            with(block4.layoutParams as ConstraintLayout.LayoutParams) {
                topToBottom = block2.id
                startToStart = ConstraintLayout.LayoutParams.UNSET
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SECOND_FRAGMENT_TAG = "SECOND_FRAGMENT_TAG"
        fun getInstance() = SecondFragment()
    }
}