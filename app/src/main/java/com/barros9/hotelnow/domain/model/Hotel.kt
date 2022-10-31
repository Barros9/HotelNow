package com.barros9.hotelnow.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Hotel(
    val id: Long,
    val name: String,
    val location: Location,
    val stars: Int,
    val checkIn: RangeHours,
    val checkOut: RangeHours,
    val contact: Contact,
    val gallery: List<String>,
    val userRating: Double,
    val price: Double,
    val currency: String
) : Parcelable

