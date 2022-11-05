package com.barros9.hotelnow.data.network

import com.barros9.hotelnow.data.network.model.HotelNetworkModel

internal interface HotelApi {
    suspend fun getHotels(): List<HotelNetworkModel>
}