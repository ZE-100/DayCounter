package com.daycounter.service.validate

import com.daycounter.other.Constants
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class InputDateValidationService {

    fun validate(date: String): Boolean {
        try {
            val sdf = SimpleDateFormat(Constants.DATE_FORMAT, Locale.GERMANY)
            sdf.parse(date)
        } catch(e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }
}
