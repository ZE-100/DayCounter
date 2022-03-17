package com.daycounter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daycounter.databinding.ActivityMainBinding
import com.daycounter.other.enum.Constants
import com.daycounter.service.data.SaveUserDataService
import com.daycounter.service.notification.CreateNotificationChannelService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val notificationChannel = CreateNotificationChannelService()
    private val preferencesHandler = SaveUserDataService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationChannel.createAnniversaryChannel(this)
        notificationChannel.createConstantChannel(this)

        preferencesHandler.loadUserData(
            getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
