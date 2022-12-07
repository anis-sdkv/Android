package com.example.homeworks

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homeworks.fragments.FirstFragment
import com.example.homeworks.services.LocationService
import com.google.android.material.snackbar.Snackbar
import java.util.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val fragmentsContainerId: Int = R.id.main_fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
    }

    private fun initFragments() {
        val fragment = supportFragmentManager.findFragmentByTag(FirstFragment.FRAGMENT_TAG)
        if (fragment != null) return
        supportFragmentManager.beginTransaction().add(
            fragmentsContainerId,
            FirstFragment.getInstance(),
            FirstFragment.FRAGMENT_TAG
        ).commit()
    }

    var requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted)
                startMyService(LocationService.START_ACTION)
            else if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))
                showPermsOnSetting()
        }

    var settings =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (checkSelfPermission(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            )
                startMyService(LocationService.START_ACTION)
        }

    private fun showPermsOnSetting() {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Give permission manually", Snackbar.LENGTH_LONG
        )
            .setAction(
                "settings".uppercase(Locale.getDefault())
            ) { v: View? -> openApplicationSettings() }
            .show()
    }

    private fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:" + getPackageName())
        )
        settings.launch(appSettingsIntent)
    }

    fun startMyService(action: String) {
        val i = Intent(this, LocationService::class.java)
        i.action = action
        startService(i)
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