package com.daycounter.service.data

import android.content.SharedPreferences
import com.daycounter.blueprint.Counter
import com.daycounter.other.Constants
import com.daycounter.other.Preferences
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DataHandlingService {

    private val sdf = SimpleDateFormat(Constants.DATE_FORMAT, Locale.GERMANY)

    fun saveData(sharedPref: SharedPreferences): Boolean {

        val editor = sharedPref.edit()

        if (Constants.MAIN_COUNTER != null)
            editor.apply {
                putString(Preferences.PERSON_ONE, Constants.MAIN_COUNTER?.personOne)
                putString(Preferences.PERSON_TWO, Constants.MAIN_COUNTER?.personTwo)
                putString(Preferences.DATE_ONE, sdf.format(Constants.MAIN_COUNTER?.startDate))

                // Setting preferences
                putBoolean(Preferences.ENABLE_NOTIFICATIONS, Constants.ENABLE_NOTIFICATIONS)
                putBoolean(Preferences.ENABLE_BACKGROUND_SERVICES, Constants.ENABLE_BACKGROUND_SERVICES)
                putBoolean(Preferences.ENABLE_DARKMODE, Constants.ENABLE_DARKMODE)
            }.apply()

        return sharedPref.getString(Preferences.PERSON_ONE, null) != null
    }

    fun loadData(sharedPref: SharedPreferences) {
        Constants.MAIN_COUNTER = try {
            // Main Counter data
                val personOne = sharedPref.getString(Preferences.PERSON_ONE, null)
                val personTwo = sharedPref.getString(Preferences.PERSON_TWO, null)
                val startDate = sdf.parse(sharedPref.getString(Preferences.DATE_ONE, null))
            // End

            // Preferences data
                Constants.ENABLE_NOTIFICATIONS = sharedPref.getBoolean(Preferences.ENABLE_NOTIFICATIONS, true)
                Constants.ENABLE_BACKGROUND_SERVICES = sharedPref.getBoolean(Preferences.ENABLE_BACKGROUND_SERVICES, true)
                Constants.ENABLE_DARKMODE = sharedPref.getBoolean(Preferences.ENABLE_DARKMODE, true)
            // End

            if (personOne != null && personTwo != null)
                 Counter(personOne, personTwo, startDate) else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
