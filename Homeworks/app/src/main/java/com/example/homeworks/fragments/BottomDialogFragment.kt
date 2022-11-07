package com.example.homeworks.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworks.R
import com.example.homeworks.adapters.DialogItemsAdapter
import com.example.homeworks.databinding.FragmentDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    override fun getTheme() = R.style.AppBottomSheetDialogTheme
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.bind(inflater.inflate(R.layout.fragment_dialog, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = DialogItemsAdapter(this)
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}
