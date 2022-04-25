package com.barros9.hotelnow.domain

import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.data.hotels.HotelsRepository
import com.barros9.hotelnow.domain.models.Hotel
import com.barros9.hotelnow.domain.models.SortType
import javax.inject.Inject

class GetHotelsOrderByUseCase @Inject constructor(
    private val repository: HotelsRepository
) {
    suspend operator fun invoke(sortType: SortType, isAsc: Boolean = false): Result<List<Hotel>> {
        return repository.getHotelsOrderBy(sortType, isAsc)
    }
}