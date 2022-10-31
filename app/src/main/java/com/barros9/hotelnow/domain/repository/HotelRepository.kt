package com.barros9.hotelnow.domain.repository

import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Result
import com.barros9.hotelnow.domain.model.SortType
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    suspend fun getHotels(sortType: SortType, isAsc: Boolean): Flow<Result<List<Hotel>>>
    suspend fun getHotelById(hotelId: Long): Result<Hotel>
}