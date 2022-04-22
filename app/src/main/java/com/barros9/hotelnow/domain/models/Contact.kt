package com.barros9.hotelnow.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val phoneNumber: String,
    val email: String
)