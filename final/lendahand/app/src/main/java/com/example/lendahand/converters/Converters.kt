package com.example.lendahand.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private var gson = Gson()

    @TypeConverter
    fun fromString(string: String?): List<String?> {
        if (string == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<String>>() {

        }.type

        return gson.fromJson(string, listType)
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return gson.toJson(list)
    }

}