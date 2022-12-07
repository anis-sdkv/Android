package com.example.homeworks.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.tools.NotificationsProvider


class LocationService : Service() {

    private var isListening: Boolean = false
    private val notificationId = 1
    private var _notificationsProvider: NotificationsProvider? = null
    private val notificationsProvider: NotificationsProvider get() = _notificationsProvider!!
    private var _locationManager: LocationManager? = null
    private val lm: LocationManager get() = _locationManager!!

    private var _gpsListener: GpsListener? = GpsListener()
    private val gpsListener: GpsListener get() = _gpsListener!!

    private var _networkListener: NetworkListener? = NetworkListener()
    private val networkListener: NetworkListener get() = _networkListener!!


    private var gpsReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent) {
            if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                lm.removeUpdates(gpsListener)
            } else {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1f, gpsListener)
            }
        }
    }

    override fun onCreate() {
        _notificationsProvider = NotificationsProvider(this)
        _locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val filter = IntentFilter()
        filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION)
        filter.addAction(Intent.CATEGORY_DEFAULT)
        registerReceiver(gpsReceiver, filter)

        startForeground(notificationId, getNotification("Waiting for coordinates..."))
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            START_ACTION -> startListen()
            STOP_ACTION -> stopService()
            else -> throw Exception()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun getNotification(text: String): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )

        return notificationsProvider.getBuilder(notificationsProvider.serviceChannel)
            .setSmallIcon(R.drawable.ic_baseline_location_on_24)
            .setContentTitle(this.packageName)
            .setContentText(text)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun startListen() {
        if (isListening) {
            Toast.makeText(this, "Service already started!", Toast.LENGTH_SHORT).show()
            return
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Allow access to location in the settings.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS provider is disabled, only network provider is used.", Toast.LENGTH_SHORT).show()
            return
        } else
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1f, gpsListener)

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 1f, networkListener)

        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show()
        isListening = true
    }

    private fun stopService() {
        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show()
        lm.removeUpdates(gpsListener);
        lm.removeUpdates(networkListener)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        _locationManager = null
        _notificationsProvider = null
        _gpsListener = null
        if (gpsReceiver != null) {
            unregisterReceiver(gpsReceiver);
            gpsReceiver = null;
        }
    }

    companion object {
        const val START_ACTION = "com.app.action.start"
        const val STOP_ACTION = "com.app.action.stop"
    }

    inner class GpsListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            val longitude = location.longitude
            val latitude = location.latitude
            notificationsProvider.showNotification(
                getNotification("Longitude: $longitude   Latitide: $latitude"),
                notificationId
            )
        }

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }

    inner class NetworkListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            val longitude = location.longitude
            val latitude = location.latitude
            notificationsProvider.showNotification(
                getNotification("Longitude: $longitude   Latitide: $latitude"),
                notificationId
            )
        }

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }
}