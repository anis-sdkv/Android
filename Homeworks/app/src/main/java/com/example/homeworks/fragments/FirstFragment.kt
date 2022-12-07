package com.example.homeworks.fragments

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentFirstBinding
import com.example.homeworks.services.LocationService
import com.google.android.material.snackbar.Snackbar


class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnStart.setOnClickListener {
                requestPermissionWithRationale()
            }

            btnStop.setOnClickListener {
                (requireActivity() as MainActivity).startMyService(LocationService.STOP_ACTION)
            }
        }
    }

    private fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "Allow access to location",
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(
                    "Allow".uppercase()
                ) { requestPerms() }
                .show()
        } else {
            requestPerms()
        }
    }

    private fun requestPerms() {
        (requireActivity() as MainActivity).requestPermissions.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "FIRST_FRAGMENT"
        fun getInstance() = FirstFragment()
    }
}