package com.barros9.hotelnow.data.hotels

import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.domain.models.Hotel
import com.barros9.hotelnow.domain.models.SortType

interface HotelsRepository {
    suspend fun getHotels(sortType: SortType, isAsc: Boolean): Result<List<Hotel>>
}