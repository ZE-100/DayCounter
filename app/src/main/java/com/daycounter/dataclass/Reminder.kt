package com.daycounter.dataclass

import java.util.*

data class Reminder(
    var title: String,
    var description: String,
    var thumbnail: Int,
    var dueDate: Date,
)
