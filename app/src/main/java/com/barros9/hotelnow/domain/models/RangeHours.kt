package com.barros9.hotelnow.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class RangeHours(
    val from: String,
    val to: String
) : Parcelable