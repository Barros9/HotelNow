package com.barros9.hotelnow.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class HotelNetworkModel(
    val id: Long,
    val name: String,
    val location: LocationNetworkModel,
    val stars: Int,
    val checkIn: RangeHoursNetworkModel,
    val checkOut: RangeHoursNetworkModel,
    val contact: ContactNetworkModel,
    val gallery: List<String>,
    val userRating: Double,
    val price: Double,
    val currency: String
)

