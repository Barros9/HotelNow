package com.barros9.hotelnow.data.network.mapper

import com.barros9.hotelnow.data.network.model.HotelNetworkModel
import com.barros9.hotelnow.domain.model.Hotel

internal fun HotelNetworkModel.mapToDomainModel() =
    Hotel(
        id = id,
        name = name,
        location = location.mapToDomainModel(),
        stars = stars,
        checkIn = checkIn.mapToDomainModel(),
        checkOut = checkOut.mapToDomainModel(),
        contact = contact.mapToDomainModel(),
        gallery = gallery,
        userRating = userRating,
        price = price,
        currency = currency
    )
