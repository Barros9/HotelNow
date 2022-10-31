package com.barros9.hotelnow.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotels")
internal data class HotelDatabaseModel(
    @PrimaryKey
    val id: Long,
    val name: String,
    val stars: Int,
    val gallery: List<String>,
    val userRating: Double,
    val price: Double,
    val currency: String
)

