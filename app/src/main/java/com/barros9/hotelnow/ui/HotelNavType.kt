package com.barros9.hotelnow.ui

import android.os.Bundle
import androidx.navigation.NavType
import com.barros9.hotelnow.domain.models.Hotel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val HotelNavType: NavType<Hotel> = object : NavType<Hotel>(false) {
    override val name: String
        get() = "hotel"

    override fun get(bundle: Bundle, key: String): Hotel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Hotel {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Hotel) {
        bundle.putParcelable(key, value)
    }
}