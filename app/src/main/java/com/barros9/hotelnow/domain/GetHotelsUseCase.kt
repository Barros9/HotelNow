package com.barros9.hotelnow.domain

import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.data.hotels.HotelsRepository
import com.barros9.hotelnow.domain.models.Hotel
import com.barros9.hotelnow.domain.models.SortType
import javax.inject.Inject

class GetHotelsUseCase @Inject constructor(
    private val repository: HotelsRepository
) {
    suspend operator fun invoke(
        sortType: SortType = SortType.Name,
        isAsc: Boolean = false
    ): Result<List<Hotel>> {
        return repository.getHotels(sortType, isAsc)
    }
}