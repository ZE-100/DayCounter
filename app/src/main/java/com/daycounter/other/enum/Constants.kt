package com.daycounter.other.enum

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.daycounter.dataclass.Counter
import com.daycounter.service.background.BackgroundThreadService
import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object Factory {
        var SDF = SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY)

        const val USER_PREFERENCES = "user-preferences"
        const val ANNIVERSARY_CHANNEL_ID = 1L
        var MAIN_COUNTER: Counter? = null
        // Setting preferences
        var ENABLE_NOTIFICATIONS = true
        var ENABLE_BACKGROUND_SERVICES = true
        var ENABLE_DARKMODE = true
        var GAY_SWITCH_ENABLED = false
        var GAY_THEME_ENABLED = false

        @SuppressLint("StaticFieldLeak")
        var CONTEXT: Context? = null

        var BACKGROUND_INTENT: Intent? = null
    }
}

class Strings {
    companion object Factory {
        const val INVALID_INPUT_DAY = "Please choose a valid day \n (1–31)"
        const val INVALID_INPUT_MONTH = "Please choose a valid month \n (1 – 12)"
        const val INVALID_INPUT_YEAR = "Counter can\'t start \n in future"
        const val FILL_IN_ALL_FIELDS = "Please fill in all fields"
        const val EASTER_EGG = "I love you <3"
        const val EMAIL_SUBJECT_SUPPORT = "Support: Daycounter [Replace with support request]"
        val EMAIL_BODY_SUPPORT = String.format("Support ticket %s\n", Random().ints())
        const val EMAIL_SUBJECT_RATE = "Rate app: Daycounter"
        const val EMAIL_BODY_RATE = ""
    }
}

class Preferences {
    companion object Factory {
        const val PERSON_ONE = "person-one"
        const val PERSON_TWO = "person-two"
        const val DATE_ONE = "start-date"
        const val DATE_DIFF = "date-diff"

        const val REMINDERS_LIST = "reminders-list"
        const val REMINDER_COUNT = "reminder-count"

        const val ENABLE_NOTIFICATIONS = "enable-notifications"
        const val ENABLE_BACKGROUND_SERVICES = "enable-background-services"
        const val ENABLE_DARKMODE = "enable-darkmode"
        const val GAY_THEME_ENABLED = "gay-theme-enabled"
        const val GAY_SWITCH_ENABLED = "gay-switch-enabled"
    }
}

class ProgressGetter {
    companion object Factory {
        fun get(days: Long): Double {
            if (days <= 100) return 100.0
            if (days <= 365) return 365.0
            if (days <= 420) return 420.0
            if (days <= 1000) return 1000.0
            if (days <= 3650) return 3650.0
            else return -1.0
        }
    }
}
