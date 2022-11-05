package com.barros9.hotelnow.data.database.mapper

import com.barros9.hotelnow.data.database.model.CheckInDatabaseModel
import com.barros9.hotelnow.domain.model.CheckIn

internal fun CheckInDatabaseModel.mapToDomainModel() =
    CheckIn(
        from = from,
        to = to
    )

internal fun CheckIn.mapFromDomainModel(hotelId: Long) =
    CheckInDatabaseModel(
        hotelId = hotelId,
        from = from,
        to = to
    )