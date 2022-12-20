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
import com.example.homeworks.data.UsersRepository
import com.example.homeworks.data.entity.User
import com.example.homeworks.data.entity.UserSettings
import com.example.homeworks.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegistrationBinding.bind(view)
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnReg.setOnClickListener {
                tryCreateNewUser(etLogin.text.toString(), etPassword.text.toString())
            }
        }
    }

    private fun tryCreateNewUser(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            lifecycleScope.launch {
                if (MainActivity.repository!!.getUserByLogin(login) != null) {
                    Toast.makeText(requireContext(), "Пользватель с таким логином уже зарегистрирован!", Toast.LENGTH_SHORT)
                        .show()
                    return@launch
                }
                val id = MainActivity.repository!!.saveUser(User(login, password))

                val pref = requireActivity().getSharedPreferences(MainActivity.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
                with(pref.edit()) {
                    putInt(MainActivity.USER_KEY, id.toInt())
                    commit()
                }

                (requireActivity() as MainActivity).replaceContainer(
                    AccountContainer.getInstance(),
                    AccountContainer.FRAGMENT_TAG
                )
            }
        } else Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "REG_FRAGMENT"
        fun getInstance() = RegistrationFragment()
    }
}