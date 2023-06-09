package com.example.flavorquest.data.local.converters

import androidx.room.TypeConverter

class ListToStringConverters {
    @TypeConverter
    fun fromListString(list: List<String>): String {
       return list.joinToString(",")
    }
    
    @TypeConverter
    fun toListString(string: String): List<String> {
        return string.split(",")
    }
}