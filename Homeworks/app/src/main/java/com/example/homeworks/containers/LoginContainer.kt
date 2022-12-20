package com.example.homeworks.containers

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homeworks.R
import com.example.homeworks.fragments.LoginFragment

class LoginContainer : Fragment(R.layout.container_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction().replace(
            R.id.login_fragments_container,
            LoginFragment.getInstance(),
            LoginFragment.FRAGMENT_TAG
        ).commit()
    }

    companion object {
        const val FRAGMENT_TAG = "LOGIN_CONTAINER"
        fun getInstance() = LoginContainer()
    }
}