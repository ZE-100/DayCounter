package com.daycounter.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.daycounter.R
import com.daycounter.databinding.ActivityMainBinding
import com.daycounter.other.Constants
import com.daycounter.service.data.DataHandlingService

class MainActivity : AppCompatActivity() {
    //TODO Stuff listed here
    //TODO On first startup: Upload avatar + names
    //TODO: Push notification each anniversary
    //TODO: Basic notification (switch off/on): Displays days and icons/names
    //TODO: kms

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create channels on startup
        createAnniversaryChannel()
        createConstantChannel()

        loadUserPreferences()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadUserPreferences() {
        val handler = DataHandlingService()

        handler.loadData(
            getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))
    }

    fun createNewNotification(notificationTitle: String, notificationText: String) {

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, getString(R.string.anniversary_channel))
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(R.string.anniversary_channel, builder.build())
        }
    }

    private fun createAnniversaryChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.anniversary_channel)
            val descriptionText = getString(R.string.anniversary_channel_desc)
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(getString(R.string.anniversary_channel), name, importance)
            mChannel.description = descriptionText

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun createConstantChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.constant_channel)
            val descriptionText = getString(R.string.constant_channel_desc)
            val importance = NotificationManager.IMPORTANCE_LOW

            val mChannel = NotificationChannel(getString(R.string.constant_channel), name, importance)
            mChannel.description = descriptionText

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}
