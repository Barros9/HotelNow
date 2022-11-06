package com.barros9.hotelnow.data.database.mapper

import com.barros9.hotelnow.data.database.model.HotelDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModelRelations
import com.barros9.hotelnow.domain.model.Hotel

internal fun HotelDatabaseModelRelations.mapToDomainModel() =
    Hotel(
        id = hotelDatabaseModel.id,
        name = hotelDatabaseModel.name,
        location = location.mapToDomainModel(),
        stars = hotelDatabaseModel.stars,
        checkIn = checkIn.mapToDomainModel(),
        checkOut = checkOut.mapToDomainModel(),
        contact = contact.mapToDomainModel(),
        gallery = hotelDatabaseModel.gallery,
        userRating = hotelDatabaseModel.userRating,
        price = hotelDatabaseModel.price,
        currency = hotelDatabaseModel.currency
    )

internal fun Hotel.mapFromDomainModel() =
    HotelDatabaseModel(
        id = id,
        name = name,
        stars = stars,
        gallery = gallery,
        userRating = userRating,
        price = price,
        currency = currency
    )
