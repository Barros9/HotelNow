package com.barros9.hotelnow.data.network.mapper

import com.barros9.hotelnow.data.network.model.CheckOutNetworkModel
import com.barros9.hotelnow.domain.model.CheckOut

internal fun CheckOutNetworkModel.mapToDomainModel() =
    CheckOut(
        from = from,
        to = to
    )