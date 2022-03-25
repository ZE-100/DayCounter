package com.daycounter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daycounter.databinding.ActivityMainBinding
import com.daycounter.other.enum.Constants
import com.daycounter.service.data.SaveUserDataService
import com.daycounter.service.notification.CreateNotificationChannelService
import android.content.Intent
import com.daycounter.service.background.BackgroundThreadService


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val notificationChannel = CreateNotificationChannelService()
    private val preferencesHandler = SaveUserDataService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Constants.CONTEXT = this
        Constants.BACKGROUND_INTENT = Intent(Constants.CONTEXT!!, BackgroundThreadService::class.java)

        notificationChannel.createAnniversaryChannel(this)
        notificationChannel.createRemindersChannel(this)

        preferencesHandler.loadUserData(
            getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))

        if (Constants.ENABLE_BACKGROUND_SERVICES)
            startService(Constants.BACKGROUND_INTENT)

        if (Constants.GAY_THEME_ENABLED)
            window.setBackgroundDrawableResource(R.drawable.background_app_pan)
        else
            window.setBackgroundDrawableResource(R.drawable.background_app)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
