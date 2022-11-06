package com.barros9.hotelnow.domain.usecase

import com.barros9.hotelnow.domain.mock.DomainMock
import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Result.Success
import com.barros9.hotelnow.domain.repository.HotelRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetHotelByIdUseCaseTest {

    @MockK
    private lateinit var hotelRepository: HotelRepository

    private lateinit var getHotelByIdUseCase: GetHotelByIdUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getHotelByIdUseCase = GetHotelByIdUseCase(hotelRepository)
    }

    @Test
    fun `use case should call repository and return a hotel`() = runTest {
        // Given
        coEvery { hotelRepository.getHotelById(any()) } returns Success(DomainMock.listOfHotel[1])

        // When
        val result = getHotelByIdUseCase(hotelId = 1)

        // Then
        coVerify { hotelRepository.getHotelById(any()) }
        assertEquals(DomainMock.listOfHotel[1], (result as Success<Hotel>).data)
    }
}
