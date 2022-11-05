package com.barros9.hotelnow.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class LocationNetworkModel(
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
)