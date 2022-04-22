package com.barros9.hotelnow.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)