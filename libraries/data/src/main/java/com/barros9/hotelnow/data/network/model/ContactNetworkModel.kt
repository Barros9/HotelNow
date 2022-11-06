package com.barros9.hotelnow.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ContactNetworkModel(
    val phoneNumber: String,
    val email: String
)