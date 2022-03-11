package com.daycounter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daycounter.databinding.ActivityMainBinding
import com.daycounter.other.enum.Constants
import com.daycounter.service.data.DataHandlingService
import com.daycounter.service.notification.CreateNotificationChannelService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val notificationChannel = CreateNotificationChannelService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create channels on startup
        notificationChannel.createAnniversaryChannel(this)
        notificationChannel.createConstantChannel(this)

        loadUserPreferences()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadUserPreferences() {
        val handler = DataHandlingService()

        handler.loadData(
            getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))
    }
}
