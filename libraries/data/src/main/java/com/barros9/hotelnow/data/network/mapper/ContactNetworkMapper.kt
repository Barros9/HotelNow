package com.barros9.hotelnow.data.network.mapper

import com.barros9.hotelnow.data.network.model.ContactNetworkModel
import com.barros9.hotelnow.domain.model.Contact

internal fun ContactNetworkModel.mapToDomainModel() =
    Contact(
        phoneNumber = phoneNumber,
        email = email
    )