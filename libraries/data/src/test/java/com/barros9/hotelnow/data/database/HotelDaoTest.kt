@file:OptIn(ExperimentalCoroutinesApi::class)

package com.barros9.hotelnow.data.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.barros9.hotelnow.data.mock.DataMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class HotelDaoTest {
    private lateinit var hotelDatabase: HotelDatabase

    @Before
    fun setup() {
        hotelDatabase = Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), HotelDatabase::class.java)
            .build()
    }

    @After
    fun tearDown() {
        hotelDatabase.close()
    }

    @Test
    fun `insert and get route entity`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel

        // When
        hotelDatabase.hotelDao().insertHotels(listOfHotelDatabaseModel.map { it.hotelDatabaseModel })
        listOfHotelDatabaseModel.forEach { hotel ->
            hotelDatabase.hotelDao().insertLocation(hotel.location)
            hotelDatabase.hotelDao().insertCheckIn(hotel.checkIn)
            hotelDatabase.hotelDao().insertCheckOut(hotel.checkOut)
            hotelDatabase.hotelDao().insertContact(hotel.contact)
        }

        val resultFromDb = hotelDatabase.hotelDao().getHotels()

        // Then
        assertEquals(resultFromDb, DataMock.listOfHotelDatabaseModel)
    }

    @Test
    fun `insert and get a single hotel database model`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel

        // When
        hotelDatabase.hotelDao().insertHotels(listOfHotelDatabaseModel.map { it.hotelDatabaseModel })
        listOfHotelDatabaseModel.forEach { hotel ->
            hotelDatabase.hotelDao().insertLocation(hotel.location)
            hotelDatabase.hotelDao().insertCheckIn(hotel.checkIn)
            hotelDatabase.hotelDao().insertCheckOut(hotel.checkOut)
            hotelDatabase.hotelDao().insertContact(hotel.contact)
        }
        val resultFromDb = hotelDatabase.hotelDao().getHotelById(3L)

        // Then
        assertEquals(resultFromDb, listOfHotelDatabaseModel.first { it.hotelDatabaseModel.id == 3L })
    }

    @Test
    fun `insert and get first hotel order by name database model`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel

        // When
        hotelDatabase.hotelDao().insertHotels(listOfHotelDatabaseModel.map { it.hotelDatabaseModel })
        listOfHotelDatabaseModel.forEach { hotel ->
            hotelDatabase.hotelDao().insertLocation(hotel.location)
            hotelDatabase.hotelDao().insertCheckIn(hotel.checkIn)
            hotelDatabase.hotelDao().insertCheckOut(hotel.checkOut)
            hotelDatabase.hotelDao().insertContact(hotel.contact)
        }
        val resultFromDb = hotelDatabase.hotelDao().getHotelsOrderByName(true)

        // Then
        assertEquals(resultFromDb.first(), listOfHotelDatabaseModel.minByOrNull { it.hotelDatabaseModel.name }!!)
    }

    @Test
    fun `insert and get last hotel order by stars database model`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel

        // When
        hotelDatabase.hotelDao().insertHotels(listOfHotelDatabaseModel.map { it.hotelDatabaseModel })
        listOfHotelDatabaseModel.forEach { hotel ->
            hotelDatabase.hotelDao().insertLocation(hotel.location)
            hotelDatabase.hotelDao().insertCheckIn(hotel.checkIn)
            hotelDatabase.hotelDao().insertCheckOut(hotel.checkOut)
            hotelDatabase.hotelDao().insertContact(hotel.contact)
        }
        val resultFromDb = hotelDatabase.hotelDao().getHotelsOrderByStars(false)

        // Then
        assertEquals(resultFromDb.first(), listOfHotelDatabaseModel.maxByOrNull { it.hotelDatabaseModel.stars }!!)
    }

    @Test
    fun `insert and get first hotel order by user rating database model`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel

        // When
        hotelDatabase.hotelDao().insertHotels(listOfHotelDatabaseModel.map { it.hotelDatabaseModel })
        listOfHotelDatabaseModel.forEach { hotel ->
            hotelDatabase.hotelDao().insertLocation(hotel.location)
            hotelDatabase.hotelDao().insertCheckIn(hotel.checkIn)
            hotelDatabase.hotelDao().insertCheckOut(hotel.checkOut)
            hotelDatabase.hotelDao().insertContact(hotel.contact)
        }
        val resultFromDb = hotelDatabase.hotelDao().getHotelsOrderByUserRating(true)

        // Then
        assertEquals(resultFromDb.first(), listOfHotelDatabaseModel.minByOrNull { it.hotelDatabaseModel.userRating }!!)
    }

    @Test
    fun `insert and get hotels order by price database model`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel

        // When
        hotelDatabase.hotelDao().insertHotels(listOfHotelDatabaseModel.map { it.hotelDatabaseModel })
        listOfHotelDatabaseModel.forEach { hotel ->
            hotelDatabase.hotelDao().insertLocation(hotel.location)
            hotelDatabase.hotelDao().insertCheckIn(hotel.checkIn)
            hotelDatabase.hotelDao().insertCheckOut(hotel.checkOut)
            hotelDatabase.hotelDao().insertContact(hotel.contact)
        }
        val resultFromDb = hotelDatabase.hotelDao().getHotelsOrderByUserRating(true)

        // Then
        assertEquals(resultFromDb, listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.price })
    }
}
