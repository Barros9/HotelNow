package com.barros9.hotelnow.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Location(
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable