package com.example.homeworks

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.homeworks.databinding.ActivityMainBinding
import com.example.homeworks.fragments.BottomDialogFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller =
            (supportFragmentManager.findFragmentById(R.id.container)
                    as NavHostFragment).navController

        with(binding) {
            bnvMain.setupWithNavController(controller)
        }
    }

    fun changeBottomNavVisibility(visibility: Int) {
        binding.bnvMain.visibility = visibility
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
}