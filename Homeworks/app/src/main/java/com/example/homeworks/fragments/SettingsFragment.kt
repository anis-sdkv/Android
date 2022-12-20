package com.example.homeworks.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.data.entity.UserAndSettings
import com.example.homeworks.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var currentUser: UserAndSettings? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
        initViews()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            cb1.setOnClickListener {
                currentUser?.settings?.setting1 = cb1.isChecked
                updateSettings()
            }
            cb2.setOnClickListener {
                currentUser?.settings?.setting2 = cb2.isChecked
                updateSettings()
            }
            cb3.setOnClickListener {
                currentUser?.settings?.setting3 = cb3.isChecked
                updateSettings()
            }
        }
    }

    private fun updateSettings() {
        lifecycleScope.launch {
            currentUser?.settings?.let { MainActivity.repository!!.updateSettings(it) }
        }
    }

    private fun initViews() {
        val pref = requireActivity().getSharedPreferences(MainActivity.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        val userId = pref.getInt(MainActivity.USER_KEY, -1)
        lifecycleScope.launch {
            currentUser = MainActivity.repository!!.getUserWithSettings(userId)
            with(binding) {
                cb1.isChecked = currentUser?.settings?.setting1!!
                cb2.isChecked = currentUser?.settings?.setting2!!
                cb3.isChecked = currentUser?.settings?.setting3!!
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "SETTINGS_FRAGMENT"
        fun getInstance() = SettingsFragment()
    }
}