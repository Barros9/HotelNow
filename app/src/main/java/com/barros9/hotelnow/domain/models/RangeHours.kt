package com.barros9.hotelnow.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class RangeHours(
    val from: String,
    val to: String
)