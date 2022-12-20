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
import com.example.homeworks.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        initFragment()
    }

    private fun initFragment() {
        with(binding) {
            btnLogin.setOnClickListener {
                tryLogin(etLogin.text.toString(), etPassword.text.toString())
            }
            btnReg.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.login_fragments_container,
                        RegistrationFragment.getInstance(),
                        RegistrationFragment.FRAGMENT_TAG
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun tryLogin(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            lifecycleScope.launch {
                val user = MainActivity.repository!!.getUserByLogin(login)
                if (user == null) {
                    Toast.makeText(requireContext(), "Пользвателя с таким логином не существует!", Toast.LENGTH_SHORT)
                        .show()
                    return@launch
                }
                if (user.password == password) {
                    val pref = requireActivity().getSharedPreferences(MainActivity.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
                    with(pref.edit()) {
                        putInt(MainActivity.USER_KEY, user.id)
                        commit()
                    }

                    (requireActivity() as MainActivity).replaceContainer(
                        AccountContainer.getInstance(),
                        AccountContainer.FRAGMENT_TAG
                    )
                } else
                    Toast.makeText(requireContext(), "Не верный пароль.", Toast.LENGTH_SHORT)
                        .show()
            }
        } else Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "LOGIN_FRAGMENT"
        fun getInstance() = LoginFragment()
    }
}