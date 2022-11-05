package com.barros9.hotelnow.data.network.mapper

import com.barros9.hotelnow.data.network.model.LocationNetworkModel
import com.barros9.hotelnow.domain.model.Location

internal fun LocationNetworkModel.mapToDomainModel() =
    Location(
        address = address,
        city = city,
        latitude = latitude,
        longitude = longitude
    )