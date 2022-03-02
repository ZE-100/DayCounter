package com.daycounter.service.calculation

import com.daycounter.other.TranslationType
import com.daycounter.other.TranslationType.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GetDateDifferenceService {

    fun getDateDifference(counterStartDate: Date?, type: TranslationType): Long {
        try {
            val currentDate = Date()

            val differenceInMillis = currentDate.time - counterStartDate!!.time

            return translateToSeconds(differenceInMillis, type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return -1
    }

    private fun translateToSeconds(differenceInMillis: Long, type: TranslationType): Long {

        val days: Long = (differenceInMillis / 86400000)

        return when(type) {
            DAYS -> days
            MONTHS -> 30 / days
            YEARS -> 365 / days
            DECADES -> 3650 / days
            else -> -1
        }
    }
}
