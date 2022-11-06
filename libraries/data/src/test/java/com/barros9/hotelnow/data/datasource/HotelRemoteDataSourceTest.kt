package com.barros9.hotelnow.data.datasource

import com.barros9.hotelnow.data.datasource.remotedatasource.HotelRemoteDataSource
import com.barros9.hotelnow.data.datasource.remotedatasource.HotelRemoteDataSourceImpl
import com.barros9.hotelnow.data.mock.DataMock
import com.barros9.hotelnow.data.network.HotelApi
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
internal class HotelRemoteDataSourceTest {

    @MockK
    private lateinit var hotelApi: HotelApi

    private lateinit var hotelRemoteDataSource: HotelRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        hotelRemoteDataSource = HotelRemoteDataSourceImpl(hotelApi)
    }

    @Test
    fun `get hotel with success`() = runTest {
        // Given
        coEvery { hotelApi.getHotels() } returns DataMock.listOfHotelNetworkModel

        // When
        val response = hotelRemoteDataSource.getHotels()

        // Then
        coVerify { hotelApi.getHotels() }
        assertEquals(DataMock.listOfHotelNetworkModel, response)
    }
}