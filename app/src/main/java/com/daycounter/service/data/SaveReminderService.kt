package com.daycounter.service.data

import com.daycounter.dataclass.Reminder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SaveReminderService {

    fun <T> setList(list: List<T>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    fun getList(serializedObject: String?): List<Reminder?>? {
        var arrayItems: List<Reminder>? = null

        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<List<Reminder?>?>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)
        }
        return arrayItems
    }
}
