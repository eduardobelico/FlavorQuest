package com.example.flavorquest.data.local.converters

import androidx.room.TypeConverter

/**
 * Conversores de lista para string para que o room receba a informação da API.
 * */

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