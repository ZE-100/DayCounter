package com.daycounter.service.validation

import com.daycounter.other.enum.Constants
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class InputDateValidationService {

    fun validate(type: Int, inputDate: String): Boolean {

        var date: Int = 0
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        if (type != 3) {
            try {
                date = Integer.parseInt(inputDate)
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }

        return when (type) {
            0 -> date in 1..31
            1 -> date in 1..12
            2 -> date in 0..currentYear
            3 -> checkSdf(inputDate)
            else -> false
        }
    }

    private fun checkSdf(inputDate: String): Boolean {
        return try {
            val sdf = SimpleDateFormat(Constants.DATE_FORMAT)
            sdf.parse(inputDate)

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
