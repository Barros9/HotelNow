package com.barros9.hotelnow.data.hotels.localdatasource

import com.barros9.hotelnow.domain.models.Hotel

interface HotelsLocalDataSource {
    suspend fun getHotels(): List<Hotel>
    suspend fun insertHotels(hotels: List<Hotel>)
    suspend fun getHotelsOrderByStars(isAsc: Boolean): List<Hotel>
    suspend fun getHotelsOrderByUserRating(isAsc: Boolean): List<Hotel>
    suspend fun getHotelsOrderByPrice(isAsc: Boolean): List<Hotel>
}