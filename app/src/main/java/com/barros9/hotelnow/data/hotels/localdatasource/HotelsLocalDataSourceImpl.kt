package com.barros9.hotelnow.data.hotels.localdatasource

import com.barros9.hotelnow.data.database.HotelDao
import com.barros9.hotelnow.domain.models.Hotel
import javax.inject.Inject

class HotelsLocalDataSourceImpl @Inject constructor(
    private val hotelDao: HotelDao
) : HotelsLocalDataSource {
    override suspend fun getHotels(): List<Hotel> {
        return hotelDao.getHotels()
    }

    override suspend fun insertHotels(hotels: List<Hotel>) {
        hotelDao.insertHotels(hotels)
    }

    override suspend fun getHotelsOrderByStars(isAsc: Boolean): List<Hotel> {
        return hotelDao.getHotelsOrderByStars(isAsc)
    }

    override suspend fun getHotelsOrderByUserRating(isAsc: Boolean): List<Hotel> {
        return hotelDao.getHotelsOrderByUserRating(isAsc)
    }

    override suspend fun getHotelsOrderByPrice(isAsc: Boolean): List<Hotel> {
        return hotelDao.getHotelsOrderByPrice(isAsc)
    }
}