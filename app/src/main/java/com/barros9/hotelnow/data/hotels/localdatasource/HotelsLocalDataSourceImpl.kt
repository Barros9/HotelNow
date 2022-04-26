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

    override suspend fun getHotelsOrderByName(isAscending: Boolean): List<Hotel> {
        return hotelDao.getHotelsOrderByName(isAscending)
    }

    override suspend fun getHotelsOrderByStars(isAscending: Boolean): List<Hotel> {
        return hotelDao.getHotelsOrderByStars(isAscending)
    }

    override suspend fun getHotelsOrderByUserRating(isAscending: Boolean): List<Hotel> {
        return hotelDao.getHotelsOrderByUserRating(isAscending)
    }

    override suspend fun getHotelsOrderByPrice(isAscending: Boolean): List<Hotel> {
        return hotelDao.getHotelsOrderByPrice(isAscending)
    }
}