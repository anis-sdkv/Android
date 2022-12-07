package com.example.homeworks.tools

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.homeworks.R

class NotificationsProvider(private val context: Context) {
    private val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val defaultChannel =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                context.getString(R.string.default_notificaiton_id),
                context.getString(R.string.default_notificaiton_channel),
                NotificationManager.IMPORTANCE_HIGH
            ).also {
                notificationManager.createNotificationChannel(it)
            }
        } else {
            null
        }

    val serviceChannel =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                context.getString(R.string.service_notificaiton_id),
                context.getString(R.string.service_notificaiton_channel),
                NotificationManager.IMPORTANCE_HIGH
            ).also {
                notificationManager.createNotificationChannel(it)
            }
        } else {
            null
        }

    fun showNotification(title: String, text: String, isBigText: Boolean) {
        var builder = getBuilder()
            .setSmallIcon(R.drawable.ic_baseline_message_24)
            .setContentTitle(title)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)

        if (isBigText)
            builder.setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(text)
            )
        else builder.setContentText(text)
        showNotification(builder.build(), currentId++)
    }

    fun showNotification(notification: Notification, notificationId: Int) {
        notificationManager.notify(notificationId, notification)
    }

    fun getBuilder(channel: NotificationChannel? = defaultChannel): NotificationCompat.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(
                context,
                channel!!.id
            )
        } else {
            NotificationCompat.Builder(context)
        }


    companion object {
        private var currentId = 0
    }
}