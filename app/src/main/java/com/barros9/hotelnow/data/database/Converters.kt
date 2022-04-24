package com.barros9.hotelnow.data.database

import androidx.room.TypeConverter
import com.barros9.hotelnow.domain.models.Contact
import com.barros9.hotelnow.domain.models.Location
import com.barros9.hotelnow.domain.models.RangeHours
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
class Converters {

    @TypeConverter
    fun locationToString(location: Location): String = Json.encodeToString(location)

    @TypeConverter
    fun stringToLocation(string: String): Location = Json.decodeFromString(string)

    @TypeConverter
    fun rangeHoursToString(rangeHours: RangeHours): String = Json.encodeToString(rangeHours)

    @TypeConverter
    fun stringToRangeHours(string: String): RangeHours = Json.decodeFromString(string)

    @TypeConverter
    fun contactToString(contact: Contact): String = Json.encodeToString(contact)

    @TypeConverter
    fun stringToContact(string: String): Contact = Json.decodeFromString(string)

    @TypeConverter
    fun galleryToString(list: List<String>) = Json.encodeToString(list)

    @TypeConverter
    fun stringToGallery(string: String): List<String> = Json.decodeFromString(string)
}