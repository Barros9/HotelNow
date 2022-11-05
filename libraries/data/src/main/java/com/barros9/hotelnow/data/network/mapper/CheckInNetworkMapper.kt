package com.barros9.hotelnow.data.network.mapper

import com.barros9.hotelnow.data.network.model.CheckInNetworkModel
import com.barros9.hotelnow.domain.model.CheckIn

internal fun CheckInNetworkModel.mapToDomainModel() =
    CheckIn(
        from = from,
        to = to
    )