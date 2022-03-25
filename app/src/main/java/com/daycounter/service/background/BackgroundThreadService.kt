package com.daycounter.service.background

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.daycounter.dataclass.Reminders
import com.daycounter.other.enum.Constants
import com.daycounter.service.notification.CreateNotificationService
import java.util.*

class BackgroundThreadService : Service() {

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private val LOG_TAG = "Timers"
    var Your_X_SECS = 86400 //Day interval

    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(LOG_TAG, "onStartCommand")
        super.onStartCommand(intent, flags, startId)
        startTimer()
        return START_STICKY
    }

    override fun onCreate() {
        Log.e(LOG_TAG, "onCreate")
    }

    override fun onDestroy() {
        Log.e(LOG_TAG, "onDestroy")
        stopTimer()
        super.onDestroy()
    }

    private fun startTimer() {
        timer = Timer()

        initializeTimerTask()

        timer!!.schedule(timerTask, 0, Your_X_SECS * 1000L)
    }

    private fun stopTimer() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    private fun initializeTimerTask() {

        val notificationService = CreateNotificationService()
        val handler = Handler()

        var updateService: UpdateService? = UpdateService()
        val counter = updateService!!.getCounter(Constants.MAIN_COUNTER)
        val reminder = updateService!!.getReminder(Reminders.get())

        timerTask = object : TimerTask() {
            override fun run() {
                handler.post {
                    if (Constants.ENABLE_NOTIFICATIONS) {
                        if (counter != null) {
                            notificationService.new("It's your Anniversary!", String
                                    .format("You've been together for %s days!", counter.dateDiff), 0)
                        }
                        if (reminder != null) {
                            notificationService.new(reminder.title, reminder.description, 1)
                        }
                    }
                }
            }
        }
        updateService = null
    }
}