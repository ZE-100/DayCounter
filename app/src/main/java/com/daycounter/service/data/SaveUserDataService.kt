package com.daycounter.service.data

import android.content.SharedPreferences
import com.daycounter.dataclass.Counter
import com.daycounter.dataclass.Reminders
import com.daycounter.other.enum.Constants
import com.daycounter.other.enum.Preferences
import java.lang.Exception

class SaveUserDataService {

    private val reminderHandler = SaveReminderService()

    fun saveUserData(sharedPref: SharedPreferences): Boolean {

        val editor = sharedPref.edit()

        if (Constants.MAIN_COUNTER != null)
            editor.apply {
                    putString(Preferences.PERSON_ONE, Constants.MAIN_COUNTER?.personOne)
                    putString(Preferences.PERSON_TWO, Constants.MAIN_COUNTER?.personTwo)
                    putString(Preferences.DATE_ONE, Constants.SDF.format(Constants.MAIN_COUNTER?.startDate!!))
                    putLong(Preferences.DATE_DIFF, Constants.MAIN_COUNTER?.dateDiff!!)

                    putString(Preferences.REMINDERS_LIST, reminderHandler.setList(Reminders.get()))

                    putBoolean(Preferences.ENABLE_NOTIFICATIONS, Constants.ENABLE_NOTIFICATIONS)
                    putBoolean(Preferences.ENABLE_BACKGROUND_SERVICES, Constants.ENABLE_BACKGROUND_SERVICES)
                    putBoolean(Preferences.ENABLE_DARKMODE, Constants.ENABLE_DARKMODE)
                    putBoolean(Preferences.GAY_THEME_ENABLED, Constants.GAY_THEME_ENABLED)
                    putBoolean(Preferences.GAY_SWITCH_ENABLED, Constants.GAY_SWITCH_ENABLED)
                }.apply()

        return sharedPref.getString(Preferences.PERSON_ONE, null) != null
    }

    fun loadUserData(sharedPref: SharedPreferences) {
        Constants.MAIN_COUNTER = try {

            val personOne = sharedPref.getString(Preferences.PERSON_ONE, null)
            val personTwo = sharedPref.getString(Preferences.PERSON_TWO, null)
            val startDateString = sharedPref.getString(Preferences.DATE_ONE, null)
            val dateDiff = sharedPref.getLong(Preferences.DATE_DIFF, -1L)

            val remindersString = sharedPref.getString(Preferences.REMINDERS_LIST, null)

            if (remindersString != null)
                Reminders.set(reminderHandler.getList(remindersString))

            Constants.ENABLE_NOTIFICATIONS = sharedPref.getBoolean(Preferences.ENABLE_NOTIFICATIONS, true)
            Constants.ENABLE_BACKGROUND_SERVICES = sharedPref.getBoolean(Preferences.ENABLE_BACKGROUND_SERVICES, true)
            Constants.ENABLE_DARKMODE = sharedPref.getBoolean(Preferences.ENABLE_DARKMODE, true)
            Constants.GAY_SWITCH_ENABLED = sharedPref.getBoolean(Preferences.GAY_SWITCH_ENABLED, false)
            Constants.GAY_THEME_ENABLED = sharedPref.getBoolean(Preferences.GAY_THEME_ENABLED, false)

            if (personOne == null || personTwo == null || startDateString == null || dateDiff == -1L)
                null

            Counter(personOne, personTwo, Constants.SDF.parse(startDateString!!), dateDiff)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
