package com.barros9.hotelnow.data.hotels

import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.domain.models.Hotel

interface HotelsRepository {
    suspend fun getHotels(): Result<List<Hotel>>
}