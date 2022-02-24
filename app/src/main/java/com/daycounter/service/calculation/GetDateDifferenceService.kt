package com.daycounter.service.calculation

import com.daycounter.other.TranslationType
import com.daycounter.other.TranslationType.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GetDateDifferenceService {

    fun getDateDifference(id: Int, type: TranslationType): Long {
        try {
            val sdf = SimpleDateFormat("dd-MM-yyyy-HH-mm-ss", Locale.GERMANY)

            val counterStartDate = sdf.parse("27-12-2020-00-00-00") //TODO Get from Id
            val currentDate = sdf.parse("24-02-2022-00-00-00")

            val differenceInMillis = currentDate.time - counterStartDate.time

            return translateToSeconds(differenceInMillis, type)
        } catch (e: ParseException) {
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
