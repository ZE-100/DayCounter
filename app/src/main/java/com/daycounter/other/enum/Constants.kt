package com.daycounter.other.enum

import android.view.View
import com.daycounter.dataclass.Counter
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object Factory {
        const val DATE_FORMAT = "dd-MM-yyyy"
        var SDF = SimpleDateFormat(DATE_FORMAT, Locale.GERMANY)

        const val USER_PREFERENCES = "user-preferences"
        const val REMINDERS = "reminders"
        const val ANNIVERSARY_CHANNEL_ID = 1L
        var MAIN_COUNTER: Counter? = null
        var REMINDER_COUNT: Long = 0
        // Setting preferences
        var ENABLE_NOTIFICATIONS = true
        var ENABLE_BACKGROUND_SERVICES = true
        var ENABLE_DARKMODE = true
    }
}

class Strings {
    companion object Factory {
        const val INVALID_INPUT_DAY = "Please choose a valid day \n (1–31)"
        const val INVALID_INPUT_MONTH = "Please choose a valid month \n (1 – 12)"
        const val INVALID_INPUT_YEAR = "Counter can\'t start \n in future"
        const val FILL_IN_ALL_FIELDS = "Please fill in all fields"
    }
}

class Preferences {
    companion object Factory {
        const val PERSON_ONE = "person-one"
        const val PERSON_TWO = "person-two"
        const val DATE_ONE = "start-date"

        const val REMINDERS_LIST = "reminders-list"
        const val REMINDER_COUNT = "reminder-count"

        const val ENABLE_NOTIFICATIONS = "enable-notifications"
        const val ENABLE_BACKGROUND_SERVICES = "enable-background-services"
        const val ENABLE_DARKMODE = "enable-darkmode"
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

class Snackbar {
    companion object Factory {
        fun displaySnackbar(s: String, view: View) {
            Snackbar.make(view, s, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
