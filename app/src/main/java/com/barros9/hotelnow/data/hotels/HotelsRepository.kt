package com.barros9.hotelnow.data.hotels

import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.domain.models.Hotel
import com.barros9.hotelnow.domain.models.SortType

interface HotelsRepository {
    suspend fun getHotels(): Result<List<Hotel>>
    suspend fun getHotelsOrderBy(sortType: SortType, isAsc: Boolean): Result<List<Hotel>>
}