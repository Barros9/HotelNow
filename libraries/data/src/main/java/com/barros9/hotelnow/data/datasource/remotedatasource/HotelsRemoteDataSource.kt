package com.barros9.hotelnow.data.datasource.remotedatasource

import com.barros9.hotelnow.data.network.model.HotelNetworkModel

internal interface HotelsRemoteDataSource {
    suspend fun getHotels(): List<HotelNetworkModel>
}