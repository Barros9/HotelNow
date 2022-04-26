package com.barros9.hotelnow.data.hotels.localdatasource

import com.barros9.hotelnow.domain.models.Hotel

interface HotelsLocalDataSource {
    suspend fun getHotels(): List<Hotel>
    suspend fun insertHotels(hotels: List<Hotel>)
    suspend fun getHotelsOrderByName(isAscending: Boolean): List<Hotel>
    suspend fun getHotelsOrderByStars(isAscending: Boolean): List<Hotel>
    suspend fun getHotelsOrderByUserRating(isAscending: Boolean): List<Hotel>
    suspend fun getHotelsOrderByPrice(isAscending: Boolean): List<Hotel>
}