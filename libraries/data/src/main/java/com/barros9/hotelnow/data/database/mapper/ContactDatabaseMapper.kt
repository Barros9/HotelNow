package com.barros9.hotelnow.data.database.mapper

import com.barros9.hotelnow.data.database.model.ContactDatabaseModel
import com.barros9.hotelnow.domain.model.Contact

internal fun ContactDatabaseModel.mapToDomainModel() =
    Contact(
        phoneNumber = phoneNumber,
        email = email
    )

internal fun Contact.mapFromDomainModel(hotelId: Long) =
    ContactDatabaseModel(
        hotelId = hotelId,
        phoneNumber = phoneNumber,
        email = email
    )