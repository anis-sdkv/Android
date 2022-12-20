package com.example.homeworks.containers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.homeworks.R
import com.example.homeworks.databinding.ContainerAccountBinding
import com.example.homeworks.fragments.AccountFragment

class AccountContainer : Fragment(R.layout.container_account) {
    private var _binding: ContainerAccountBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ContainerAccountBinding.bind(view)
        with(binding) {
            val controller =
                (childFragmentManager.findFragmentById(R.id.account_fragments_container)
                        as NavHostFragment).navController
            bnvAccount.setupWithNavController(controller)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "ACCOUNT_CONTAINER"
        fun getInstance() = AccountContainer()
    }
}