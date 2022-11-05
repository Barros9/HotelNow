package com.barros9.hotelnow.data.database.mapper

import com.barros9.hotelnow.data.database.model.LocationDatabaseModel
import com.barros9.hotelnow.domain.model.Location

internal fun LocationDatabaseModel.mapToDomainModel() =
    Location(
        address = address,
        city = city,
        latitude = latitude,
        longitude = longitude
    )

internal fun Location.mapFromDomainModel(hotelId: Long) =
    LocationDatabaseModel(
        hotelId = hotelId,
        address = address,
        city = city,
        latitude = latitude,
        longitude = longitude
    )