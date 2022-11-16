package com.example.homeworks.tools

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.homeworks.MainActivity
import com.example.homeworks.R

class NotificationsProvider(private val context: Context) {
    private val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private val channel = setChannel(context)

    // не работает
    private val vibrations = arrayOf(50L, 100L, 150L, 200L, 250L).toLongArray()
    private val soundUri =
        Uri.parse("android.resource://" + context.packageName + "/" + R.raw.notification_sound)

    private val pending: PendingIntent? =
        PendingIntent.getActivity(
            context,
            100,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_ONE_SHOT
        )

    fun ShowNotification(title: String, text: String, isBigText: Boolean) {
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
        ShowNotification(builder)
    }

    fun ShowNotification(builder: NotificationCompat.Builder) {
        notificationManager.notify(notificationId++, builder.build())
    }

    fun getBuilder(): NotificationCompat.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(
                context,
                channel!!.id
            )
        } else {
            NotificationCompat.Builder(context)
                .setVibrate(vibrations)
                .setSound(soundUri)
        }
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setSmallIcon(R.drawable.ic_baseline_message_24)
            .setAutoCancel(true)
            .setContentIntent(pending)



    private fun setChannel(context: Context): NotificationChannel? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                context.getString(R.string.default_notificaiton_id),
                context.getString(R.string.default_notificaiton_channel),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                val attribute = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()

                setSound(soundUri, attribute)
                vibrationPattern = vibrations
            }.also {
                notificationManager.createNotificationChannel(it)
            }
        } else {
            null
        }
    companion object{
        private var notificationId = 0
    }
}