package com.daycounter.other

import android.view.View
import com.google.android.material.snackbar.Snackbar

class Constants {
    companion object Factory {
        const val DATE_FORMAT = "dd-MM-yyyy-HH-mm-ss"
        const val MAIN_COUNTER = "main-counter"
        const val ANNIVERSARY_CHANNEL_ID = 1L
        var NOTIFICATIONS_ENABLED = true
        var RUN_IN_BACKGROUND_ENABLED = true
    }
}

class Strings {
    companion object Factory {
        const val P1 = "person-one"
        const val P2 = "person-two"
        const val D1 = "start-date"
        const val INVALID_INPUT = "Invalid input!"
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
