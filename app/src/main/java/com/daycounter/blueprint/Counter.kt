package com.daycounter.blueprint

import lombok.Getter
import lombok.Setter
import java.util.*

@Getter
@Setter
class Counter {

    private var personOne: String = ""
    private var personTwo: String = ""
    private var startDate: Date = Date()
}