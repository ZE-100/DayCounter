package com.daycounter.service.background

import com.daycounter.dataclass.Counter
import com.daycounter.dataclass.Reminder
import com.daycounter.dataclass.Reminders
import com.daycounter.other.enum.Constants
import com.daycounter.other.enum.ProgressGetter
import com.daycounter.other.enum.TranslationType
import com.daycounter.service.date.DateDifferenceService

class UpdateService {

    private val dateDiff = DateDifferenceService()

    fun getCounter(counter: Counter?): Counter? {
        if (counter != null) {
            val days = counter.dateDiff
            val progress = (100 / ProgressGetter.get(days!!)) * days

            if (progress == 100.0)
                return counter
        }
        return null
    }

    fun getReminder(reminders: List<Reminder>): Reminder? {
        if (reminders.isNotEmpty()) {

            reminders.forEach {
                val days = dateDiff.getDateDifference(it.dueDate, TranslationType.DAYS)
                val progress = (100 / ProgressGetter.get(days)) * days

                if (progress == 0.0)
                    return it
            }
        }
        return null
    }
}