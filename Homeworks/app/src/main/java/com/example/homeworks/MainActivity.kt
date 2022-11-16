package com.example.homeworks

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homeworks.Fragments.FirstFragment
import com.example.homeworks.tools.NotificationsProvider

class MainActivity : AppCompatActivity(R.layout.activity_main){

    private val fragmentsContainerId: Int = R.id.main_fragments_container
    var provider: NotificationsProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
        provider = NotificationsProvider(this)
    }

    private fun initFragments() {
        val fragment = supportFragmentManager.findFragmentByTag(FirstFragment.FIRST_FRAGMENT_TAG)
        if (fragment != null) return
        supportFragmentManager.beginTransaction().add(
            fragmentsContainerId,
            FirstFragment.getInstance(),
            FirstFragment.FIRST_FRAGMENT_TAG
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

    override fun onDestroy() {
        super.onDestroy()
        provider = null
    }
}