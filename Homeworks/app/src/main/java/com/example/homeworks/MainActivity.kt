package com.example.homeworks

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.homeworks.containers.AccountContainer
import com.example.homeworks.containers.LoginContainer
import com.example.homeworks.data.AppDataBase
import com.example.homeworks.data.UsersRepository
import com.example.homeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val fragmentsContainerId: Int = R.id.activity_fragments_container
    private var currentContainer: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        repository = UsersRepository(this)
        setContentView(binding.root)
        initContainer()
    }

    private fun initContainer() {
        val pref = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)
        val id = pref.getInt(USER_KEY, -1)
        if (id == -1) {
            currentContainer = LoginContainer.getInstance()
            replaceContainer(currentContainer!!, LoginContainer.FRAGMENT_TAG)
        } else {
            currentContainer = AccountContainer.getInstance()
            replaceContainer(currentContainer!!, AccountContainer.FRAGMENT_TAG)
        }
    }

    fun replaceContainer(container: Fragment, tag: String) {
        currentContainer = container
        supportFragmentManager.beginTransaction().replace(
            fragmentsContainerId,
            container,
            tag
        ).commit()
    }

    override fun onBackPressed() {
        if (currentContainer!!.childFragmentManager.backStackEntryCount > 0) {
            currentContainer!!.childFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        _binding = null
        currentContainer = null
        repository = null
        super.onDestroy()
    }

    companion object {
        const val LOGIN_PREFERENCES = "LOGIN_PREF"
        const val USER_KEY = "USER_KEY"
        var repository: UsersRepository? = null
    }
}