package com.barros9.hotelnow.domain.model

data class Hotel(
    val id: Long,
    val name: String,
    val location: Location,
    val stars: Int,
    val checkIn: CheckIn,
    val checkOut: CheckOut,
    val contact: Contact,
    val gallery: List<String>,
    val userRating: Double,
    val price: Double,
    val currency: String
)
