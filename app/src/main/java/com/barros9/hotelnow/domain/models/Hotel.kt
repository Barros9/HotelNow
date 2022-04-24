package com.barros9.hotelnow.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "hotels")
@Serializable
@Parcelize
data class Hotel(
    @PrimaryKey
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

