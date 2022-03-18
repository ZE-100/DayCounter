package com.daycounter.dataclass

class Reminders {
    companion object Factory {
        var reminders: MutableList<Reminder> = ArrayList()

        fun get(): List<Reminder> {
            return reminders
        }

        fun set(reminders: List<Reminder?>?) {
            this.reminders = reminders as MutableList<Reminder>
        }

        fun add(reminder: Reminder) {
            reminders.add(reminder)
        }

        fun remove(itemPos: Int) {
            reminders.removeAt(itemPos)
        }
    }
}