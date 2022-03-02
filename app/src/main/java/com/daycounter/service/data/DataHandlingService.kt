package com.daycounter.service.data

import android.content.SharedPreferences
import com.daycounter.blueprint.Counter
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class DataHandlingService {
    // Once called at app startup to retrieve counter information, images and names
    // Called on data manipulation

    private val sdf = SimpleDateFormat("dd-MM-yyyy-HH-mm-ss", Locale.GERMANY)


    fun saveData(sharedPref: SharedPreferences, counter: Counter): Boolean {

        val editor = sharedPref.edit()

        editor.apply {
              putString("person-one", counter.personOne)
              putString("person-two", counter.personTwo)
              putString("start-date", sdf.format(counter.startDate))
        }.apply()

        return sharedPref.getString("person-one", null) != null
    }

    fun loadData(sharedPref: SharedPreferences): Counter? {
        var startDate = Date()

        try {
            val startDateString = sharedPref.getString("start-date", null)
            print(startDateString)
            startDate = sdf.parse(startDateString)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val personOne = sharedPref.getString("person-one", null)
        val personTwo = sharedPref.getString("person-two", null)

        return if (personOne != null && personTwo != null)
            Counter(personOne, personTwo, startDate) else null
    }
}