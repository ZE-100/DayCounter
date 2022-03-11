package com.daycounter.service.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.daycounter.R

class CreateNotificationChannelService {

    fun createAnniversaryChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.anniversary_channel)
            val descriptionText = context.getString(R.string.anniversary_channel_desc)
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(context.getString(R.string.anniversary_channel), name, importance)
            mChannel.description = descriptionText

            val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    fun createConstantChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.constant_channel)
            val descriptionText = context.getString(R.string.constant_channel_desc)
            val importance = NotificationManager.IMPORTANCE_LOW

            val mChannel = NotificationChannel(context.getString(R.string.constant_channel), name, importance)
            mChannel.description = descriptionText

            val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}