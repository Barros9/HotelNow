package com.barros9.hotelnow.data.hotels.remotedatasource

import com.barros9.hotelnow.domain.models.Hotel

interface HotelsRemoteDataSource {
    suspend fun getHotels(): List<Hotel>
}