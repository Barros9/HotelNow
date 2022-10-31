package com.barros9.hotelnow.domain.usecase

import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Result
import com.barros9.hotelnow.domain.model.SortType
import com.barros9.hotelnow.domain.repository.HotelRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetHotelsUseCase @Inject constructor(
    private val repository: HotelRepository
) {
    suspend operator fun invoke(
        sortType: SortType = SortType.Name,
        isAsc: Boolean = false
    ): Flow<Result<List<Hotel>>> =
        repository.getHotels(sortType, isAsc)
}