package com.barros9.hotelnow.data.database.mapper

import com.barros9.hotelnow.data.database.model.RangeHoursDatabaseModel
import com.barros9.hotelnow.domain.model.RangeHours

internal fun RangeHoursDatabaseModel.mapToDomainModel() =
    RangeHours(
        from = from,
        to = to
    )

internal fun RangeHours.mapFromDomainModel(hotelId: Long) =
    RangeHoursDatabaseModel(
        hotelId = hotelId,
        from = from,
        to = to
    )