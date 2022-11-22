package com.example.homeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.homeworks.Fragments.FirstFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val fragmentsContainerId: Int = R.id.main_fragments_container
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction().add(
            fragmentsContainerId,
            FirstFragment.getInstance(),
            FirstFragment.FRAGMENT_TAG
        ).commit()
    }

    fun replaceFragment(fragment: Fragment, tag: String, detachCurrent: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()

        if (detachCurrent) {
            supportFragmentManager.findFragmentById(fragmentsContainerId)?.let { currentFragment ->
                transaction.detach(currentFragment)
            }
        }

        transaction
            .replace(fragmentsContainerId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}