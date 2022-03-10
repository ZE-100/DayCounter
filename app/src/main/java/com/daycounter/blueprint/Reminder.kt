package com.daycounter.blueprint

import java.util.*

data class Reminder(
    var title: String,
    var description: String,
    var thumbnail: Int,
    var dueDate: Date,
)
