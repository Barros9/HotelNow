package com.barros9.hotelnow.domain.usecase

import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Result
import com.barros9.hotelnow.domain.repository.HotelRepository
import javax.inject.Inject

class GetHotelByIdUseCase @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(hotelId: Long): Result<Hotel> =
        hotelRepository.getHotelById(hotelId)
}
