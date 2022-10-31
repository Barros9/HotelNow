package com.barros9.hotelnow.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class RangeHoursNetworkModel(
    val from: String,
    val to: String
)