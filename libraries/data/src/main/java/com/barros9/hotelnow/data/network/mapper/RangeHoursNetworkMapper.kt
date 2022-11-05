package com.barros9.hotelnow.data.network.mapper

import com.barros9.hotelnow.data.network.model.RangeHoursNetworkModel
import com.barros9.hotelnow.domain.model.RangeHours

internal fun RangeHoursNetworkModel.mapToDomainModel() =
    RangeHours(
        from = from,
        to = to
    )