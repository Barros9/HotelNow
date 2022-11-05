package com.barros9.hotelnow.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class Converters {
    @TypeConverter
    fun listToString(list: List<String>) = Json.encodeToString(list)

    @TypeConverter
    fun stringToList(string: String): List<String> = Json.decodeFromString(string)
}