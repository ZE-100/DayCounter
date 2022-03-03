package com.daycounter.service.data

import android.content.SharedPreferences
import com.daycounter.blueprint.Counter
import com.daycounter.other.Constants
import com.daycounter.other.Strings
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DataHandlingService {

    private val sdf = SimpleDateFormat(Constants.DATE_FORMAT, Locale.GERMANY)

    fun saveData(sharedPref: SharedPreferences, counter: Counter): Boolean {

        val editor = sharedPref.edit()

        editor.apply {
              putString(Strings.P1, counter.personOne)
              putString(Strings.P2, counter.personTwo)
              putString(Strings.D1, sdf.format(counter.startDate))
        }.apply()

        return sharedPref.getString(Strings.P1, null) != null
    }

    fun loadData(sharedPref: SharedPreferences): Counter? {
        var startDate = Date()

        try {
            val startDateString = sharedPref.getString(Strings.D1, null)
            print(startDateString)
            startDate = sdf.parse(startDateString)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val personOne = sharedPref.getString(Strings.P1, null)
        val personTwo = sharedPref.getString(Strings.P2, null)

        return if (personOne != null && personTwo != null)
            Counter(personOne, personTwo, startDate) else null
    }
}