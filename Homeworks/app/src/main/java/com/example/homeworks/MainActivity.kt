package com.example.homeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.homeworks.Fragments.MainFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val fragmentsContainerId: Int = R.id.main_fragments_container
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        initFragments()
    }

    private fun initFragments() {
        val fragment = supportFragmentManager.findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG)
        if (fragment != null) return
        supportFragmentManager.beginTransaction().add(
            fragmentsContainerId,
            MainFragment.getInstance(),
            MainFragment.MAIN_FRAGMENT_TAG
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
}