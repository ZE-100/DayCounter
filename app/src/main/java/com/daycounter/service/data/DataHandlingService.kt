package com.daycounter.service.data

import android.content.SharedPreferences
import com.daycounter.blueprint.Counter
import com.daycounter.other.Constants
import com.daycounter.other.Preferences
import com.daycounter.other.Strings
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

                putBoolean(Preferences.NOTIFICATIONS_ENABLED, Constants.NOTIFICATIONS_ENABLED)
                putBoolean(Preferences.RUN_IN_BACKGROUND_ENABLED, Constants.RUN_IN_BACKGROUND_ENABLED)
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
                Constants.NOTIFICATIONS_ENABLED = sharedPref.getBoolean(Preferences.NOTIFICATIONS_ENABLED, true)
                Constants.RUN_IN_BACKGROUND_ENABLED = sharedPref.getBoolean(Preferences.RUN_IN_BACKGROUND_ENABLED, true)
            // End

            if (personOne != null && personTwo != null)
                 Counter(personOne, personTwo, startDate) else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
