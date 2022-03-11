package com.daycounter.service.data

import android.content.Context
import android.content.SharedPreferences
import com.daycounter.dataclass.Reminder
import com.daycounter.other.enum.Constants
import com.daycounter.other.enum.Preferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SaveReminderService(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Constants.REMINDERS, Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()

    fun <T> setList(list: List<T>?) {
        val gson = Gson()
        val json: String = gson.toJson(list)
        set(Preferences.REMINDERS_LIST, json)
    }

    operator fun set(key: String?, value: String?) {
        editor.putString(key, value)
        editor.commit()
    }

    fun getList(): List<Reminder?>? {
        var arrayItems: List<Reminder>? = null
        val serializedObject = sharedPreferences.getString(Preferences.REMINDERS_LIST, null)

        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<List<Reminder?>?>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)
        }
        return arrayItems
    }
}