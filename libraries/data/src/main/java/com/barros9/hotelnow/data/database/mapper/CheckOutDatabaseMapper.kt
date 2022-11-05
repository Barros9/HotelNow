package com.barros9.hotelnow.data.database.mapper

import com.barros9.hotelnow.data.database.model.CheckOutDatabaseModel
import com.barros9.hotelnow.domain.model.CheckOut

internal fun CheckOutDatabaseModel.mapToDomainModel() =
    CheckOut(
        from = from,
        to = to
    )

internal fun CheckOut.mapFromDomainModel(hotelId: Long) =
    CheckOutDatabaseModel(
        hotelId = hotelId,
        from = from,
        to = to
    )