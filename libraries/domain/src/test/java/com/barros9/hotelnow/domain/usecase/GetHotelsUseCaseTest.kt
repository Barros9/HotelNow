package com.barros9.hotelnow.domain.usecase

import app.cash.turbine.test
import com.barros9.hotelnow.domain.mock.DomainMock
import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Result.Success
import com.barros9.hotelnow.domain.repository.HotelRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetHotelsUseCaseTest {

    @MockK
    private lateinit var hotelRepository: HotelRepository

    private lateinit var getHotelsUseCase: GetHotelsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getHotelsUseCase = GetHotelsUseCase(hotelRepository)
    }

    @Test
    fun `use case should call repository and return a list of hotel`() = runTest {
        // Given
        coEvery { hotelRepository.getHotels(any(), any()) } returns flowOf(Success(DomainMock.listOfHotel))

        // When
        getHotelsUseCase().test {
            val result = awaitItem()
            awaitComplete()

            // Then
            coVerify { hotelRepository.getHotels(any(), any()) }
            assertEquals(DomainMock.listOfHotel, (result as Success<List<Hotel>>).data)
        }
    }
}