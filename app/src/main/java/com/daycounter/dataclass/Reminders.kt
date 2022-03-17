package com.daycounter.dataclass

import android.os.Build
import androidx.annotation.RequiresApi

class Reminders {
    companion object Factory {
        var reminders: MutableList<Reminder> = ArrayList()

        fun getList(): List<Reminder> {
            return reminders
        }

        fun add(reminder: Reminder) {
            reminders.add(reminder)
        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun remove(id: Long) {
            reminders.removeIf {
                it.id == id
            }
        }

        fun setList(reminders: List<Reminder?>?) {
            this.reminders = reminders as MutableList<Reminder>
        }
    }
}