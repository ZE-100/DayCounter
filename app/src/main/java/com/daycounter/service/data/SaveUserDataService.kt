package com.daycounter.service.data

import android.content.SharedPreferences
import com.daycounter.dataclass.Counter
import com.daycounter.dataclass.Reminders
import com.daycounter.other.enum.Constants
import com.daycounter.other.enum.Preferences
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class SaveUserDataService {

    private val reminderHandler = SaveReminderService()

    fun saveUserData(sharedPref: SharedPreferences): Boolean {

        val editor = sharedPref.edit()

        if (Constants.MAIN_COUNTER != null)
            editor.apply {
                putString(Preferences.PERSON_ONE, Constants.MAIN_COUNTER?.personOne)
                putString(Preferences.PERSON_TWO, Constants.MAIN_COUNTER?.personTwo)
                putString(Preferences.DATE_ONE, Constants.SDF.format(Constants.MAIN_COUNTER?.startDate!!))

                putLong(Preferences.REMINDER_COUNT, Constants.REMINDER_COUNT)
                putString(Preferences.REMINDERS_LIST, reminderHandler.setList(Reminders.getList()))

                putBoolean(Preferences.ENABLE_NOTIFICATIONS, Constants.ENABLE_NOTIFICATIONS)
                putBoolean(Preferences.ENABLE_BACKGROUND_SERVICES, Constants.ENABLE_BACKGROUND_SERVICES)
                putBoolean(Preferences.ENABLE_DARKMODE, Constants.ENABLE_DARKMODE)
            }.apply()

        return sharedPref.getString(Preferences.PERSON_ONE, null) != null
    }

    fun loadUserData(sharedPref: SharedPreferences) {
        Constants.MAIN_COUNTER = try {

            val personOne = sharedPref.getString(Preferences.PERSON_ONE, null)
            val personTwo = sharedPref.getString(Preferences.PERSON_TWO, null)
            val startDate = Constants.SDF.parse(sharedPref.getString(Preferences.DATE_ONE, null)!!)

            Constants.REMINDER_COUNT = sharedPref.getLong(Preferences.REMINDER_COUNT, 0)
            Reminders.setList(reminderHandler.getList(sharedPref.getString(Preferences.REMINDERS_LIST, null)))

            Constants.ENABLE_NOTIFICATIONS = sharedPref.getBoolean(Preferences.ENABLE_NOTIFICATIONS, true)
            Constants.ENABLE_BACKGROUND_SERVICES = sharedPref.getBoolean(Preferences.ENABLE_BACKGROUND_SERVICES, true)
            Constants.ENABLE_DARKMODE = sharedPref.getBoolean(Preferences.ENABLE_DARKMODE, true)

            if (personOne != null && personTwo != null)
                 Counter(personOne, personTwo, startDate) else null

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
