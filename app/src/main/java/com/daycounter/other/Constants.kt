package com.daycounter.other

import android.view.View
import com.daycounter.blueprint.Counter
import com.google.android.material.snackbar.Snackbar

class Constants {
    companion object Factory {
        const val DATE_FORMAT = "dd-MM-yyyy-HH-mm-ss"
        const val USER_PREFERENCES = "user-preferences"
        const val ANNIVERSARY_CHANNEL_ID = 1L
        var MAIN_COUNTER: Counter? = null
        // Setting preferences
        var ENABLE_NOTIFICATIONS = true
        var ENABLE_BACKGROUND_SERVICES = true
        var ENABLE_DARKMODE = true
    }
}

class Strings {
    companion object Factory {
        const val INVALID_INPUT = "Invalid input!"
    }
}

class Preferences {
    companion object Factory {
        const val PERSON_ONE = "person-one"
        const val PERSON_TWO = "person-two"
        const val DATE_ONE = "start-date"
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
