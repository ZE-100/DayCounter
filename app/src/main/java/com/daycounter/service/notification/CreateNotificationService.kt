package com.daycounter.service.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.daycounter.MainActivity
import com.daycounter.R
import com.daycounter.other.enum.Constants

class CreateNotificationService {

    private val context: Context = Constants.CONTEXT!!

    fun new(notificationTitle: String, notificationText: String, notificationChannel: Int) {

        val channel = if (notificationChannel == 0)
            R.string.anniversary_channel else R.string.reminders_channel

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, context.getString(channel))
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(channel, builder.build())
        }
    }
}