package com.example.homeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.homeworks.Fragments.FirstFragment
import com.example.homeworks.Fragments.SecondFragment
import com.example.homeworks.Fragments.ThirdFragment

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
            FirstFragment.FIRST_FRAGMENT_TAG
        ).commit()
        replaceFragment(SecondFragment.getInstance(),SecondFragment.SECOND_FRAGMENT_TAG, false)
        replaceFragment(ThirdFragment.getInstance(),ThirdFragment.THIRD_FRAGMENT_TAG, false)
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun replaceFragment(fragment: Fragment, tag: String, detachCurrent: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()

        if (detachCurrent) {
            supportFragmentManager.findFragmentById(fragmentsContainerId)?.let { currentFragment ->
                transaction.detach(currentFragment)
            }
        }

        transaction
//            .add(fragmentsContainerId, fragment, tag)
            .replace(fragmentsContainerId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}