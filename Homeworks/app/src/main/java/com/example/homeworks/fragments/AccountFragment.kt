package com.example.homeworks.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.containers.AccountContainer
import com.example.homeworks.containers.LoginContainer
import com.example.homeworks.data.entity.User
import com.example.homeworks.data.entity.UserAndSettings
import com.example.homeworks.databinding.FragmentAccountBinding
import kotlinx.coroutines.launch

class AccountFragment : Fragment(R.layout.fragment_account) {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private var currentUser: UserAndSettings? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAccountBinding.bind(view)
        initListeners()
        initViews()
    }

    private fun initListeners() {
        with(binding) {
            btnEdit.setOnClickListener {
                tvCurrentLogin.visibility = View.GONE
                etLogin.setText(tvCurrentLogin.text)
                etLogin.visibility = View.VISIBLE
                bntSave.visibility = View.VISIBLE
            }

            bntSave.setOnClickListener {
                val newLogin = etLogin.text.toString()
                lifecycleScope.launch {
                    if (newLogin.isEmpty() || MainActivity.repository!!.getUserByLogin(newLogin) != null) {
                        Toast.makeText(requireContext(), "Логин занят", Toast.LENGTH_SHORT).show()
                        return@launch
                    }

                    currentUser?.user?.login = newLogin
                    currentUser?.user?.let { it1 -> MainActivity.repository!!.updateUser(it1) }

                    with(binding) {
                        tvCurrentLogin.text = newLogin
                        tvCurrentLogin.visibility = View.VISIBLE
                        etLogin.visibility = View.GONE
                        bntSave.visibility = View.GONE
                    }
                }
            }

            btnExit.setOnClickListener {
                val pref = requireActivity().getSharedPreferences(MainActivity.LOGIN_PREFERENCES, Context.MODE_PRIVATE)
                pref.edit().remove(MainActivity.USER_KEY).apply()
                (requireActivity() as MainActivity).replaceContainer(
                    LoginContainer.getInstance(),
                    LoginContainer.FRAGMENT_TAG
                )
            }
        }
    }

    private fun initViews() {
        val pref = requireActivity().getSharedPreferences(MainActivity.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        val userId = pref.getInt(MainActivity.USER_KEY, -1)

        lifecycleScope.launch {
            currentUser = MainActivity.repository!!.getUserWithSettings(userId)
            with(binding) {
                tvCurrentLogin.text = currentUser?.user?.login
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "ACCOUNT_FRAGMENT"
        fun getInstance() = AccountFragment()
    }
}