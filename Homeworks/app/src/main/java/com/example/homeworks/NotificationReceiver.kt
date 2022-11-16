package com.example.homeworks

import android.R
import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Vibrator
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.homeworks.Fragments.FirstFragment
import com.example.homeworks.tools.NotificationsProvider
import java.util.*


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras!!
        val title = bundle.getString("title")
        val text = bundle.getString("text")
        val expanded = bundle.getString("expanded")

        val provider = NotificationsProvider(context)
        var builder = provider.getBuilder()
            .setContentTitle(title)
            .setContentText(text)
        expanded?.let {
            builder.setStyle(NotificationCompat.BigTextStyle().bigText(expanded))
        }

        provider.ShowNotification(builder)
    }
}