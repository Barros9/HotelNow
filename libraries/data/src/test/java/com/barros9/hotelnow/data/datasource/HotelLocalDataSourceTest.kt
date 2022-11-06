package com.barros9.hotelnow.data.datasource

import com.barros9.hotelnow.data.database.HotelDao
import com.barros9.hotelnow.data.datasource.localdatasource.HotelLocalDataSource
import com.barros9.hotelnow.data.datasource.localdatasource.HotelLocalDataSourceImpl
import com.barros9.hotelnow.data.mock.DataMock
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
internal class HotelLocalDataSourceTest {

    @MockK
    private lateinit var hotelDao: HotelDao

    private lateinit var hotelLocalDataSource: HotelLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        hotelLocalDataSource = HotelLocalDataSourceImpl(hotelDao = hotelDao)
    }

    @Test
    fun `get hotel with success`() = runTest {
        // Given
        coEvery { hotelDao.getHotels() } returns DataMock.listOfHotelDatabaseModel

        // When
        val result = hotelLocalDataSource.getHotels()

        // Then
        coVerify { hotelDao.getHotels() }
        assertEquals(DataMock.listOfHotelDatabaseModel, result)
    }

    @Test
    fun `save hotel with success`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel
        coEvery { hotelDao.insertHotels(any()) } returns Unit
        coEvery { hotelDao.insertLocation(any()) } returns Unit
        coEvery { hotelDao.insertCheckIn(any()) } returns Unit
        coEvery { hotelDao.insertCheckOut(any()) } returns Unit
        coEvery { hotelDao.insertContact(any()) } returns Unit

        // When
        hotelLocalDataSource.insertHotels(listOfHotelDatabaseModel.map { it.hotelDatabaseModel })
        listOfHotelDatabaseModel.forEach { hotel ->
            hotelLocalDataSource.insertLocation(hotel.location)
            hotelLocalDataSource.insertCheckIn(hotel.checkIn)
            hotelLocalDataSource.insertCheckOut(hotel.checkOut)
            hotelLocalDataSource.insertContact(hotel.contact)
        }

        // Then
        coVerify { hotelDao.insertHotels(any()) }
        coVerify { hotelDao.insertLocation(any()) }
        coVerify { hotelDao.insertCheckIn(any()) }
        coVerify { hotelDao.insertCheckOut(any()) }
        coVerify { hotelDao.insertContact(any()) }
    }

    @Test
    fun `get hotel by id with success`() = runTest {
        // Given
        coEvery { hotelDao.getHotelById(any()) } returns DataMock.listOfHotelDatabaseModel[1]

        // When
        val result = hotelLocalDataSource.getHotelById(1L)

        // Then
        coVerify { hotelDao.getHotelById(any()) }
        assertEquals(DataMock.listOfHotelDatabaseModel[1], result)
    }

    @Test
    fun `get hotel order by name with success`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.name }
        coEvery { hotelDao.getHotelsOrderByName(any()) } returns listOfHotelDatabaseModel

        // When
        val result = hotelLocalDataSource.getHotelsOrderByName(true)

        // Then
        coVerify { hotelDao.getHotelsOrderByName(any()) }
        assertEquals(listOfHotelDatabaseModel, result)
    }

    @Test
    fun `get hotel order by stars with success`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.stars }
        coEvery { hotelDao.getHotelsOrderByStars(any()) } returns listOfHotelDatabaseModel

        // When
        val result = hotelLocalDataSource.getHotelsOrderByStars(true)

        // Then
        coVerify { hotelDao.getHotelsOrderByStars(any()) }
        assertEquals(listOfHotelDatabaseModel, result)
    }

    @Test
    fun `get hotel order by user rating with success`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.userRating }
        coEvery { hotelDao.getHotelsOrderByUserRating(any()) } returns listOfHotelDatabaseModel

        // When
        val result = hotelLocalDataSource.getHotelsOrderByUserRating(true)

        // Then
        coVerify { hotelDao.getHotelsOrderByUserRating(any()) }
        assertEquals(listOfHotelDatabaseModel, result)
    }

    @Test
    fun `get hotel order by price with success`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.price }
        coEvery { hotelDao.getHotelsOrderByPrice(any()) } returns listOfHotelDatabaseModel

        // When
        val result = hotelLocalDataSource.getHotelsOrderByPrice(true)

        // Then
        coVerify { hotelDao.getHotelsOrderByPrice(any()) }
        assertEquals(listOfHotelDatabaseModel, result)
    }
}