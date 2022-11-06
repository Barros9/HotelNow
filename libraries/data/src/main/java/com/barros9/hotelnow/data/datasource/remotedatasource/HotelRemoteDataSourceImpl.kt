package com.barros9.hotelnow.data.datasource.remotedatasource

import com.barros9.hotelnow.data.network.HotelApi
import com.barros9.hotelnow.data.network.model.HotelNetworkModel
import javax.inject.Inject

internal class HotelRemoteDataSourceImpl @Inject constructor(
    private val hotelApi: HotelApi
) : HotelRemoteDataSource {
    override suspend fun getHotels(): List<HotelNetworkModel> = hotelApi.getHotels()
}
