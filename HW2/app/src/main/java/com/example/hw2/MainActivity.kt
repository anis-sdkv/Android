package com.example.hw2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hw2.fragments.FirstFragment


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val fragmentsContainerId: Int = R.id.main_fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(
            fragmentsContainerId,
            FirstFragment.getInstance(),
            FirstFragment.FIRST_FRAGMENT_TAG
        ).commit()
    }

    fun addFragment(fragment: Fragment, tag: String, detachCurrent: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()

        if (detachCurrent) {
            supportFragmentManager.findFragmentById(fragmentsContainerId)?.let { currentFragment ->
                transaction.detach(currentFragment)
            }
        }

        transaction
            .add(fragmentsContainerId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
}