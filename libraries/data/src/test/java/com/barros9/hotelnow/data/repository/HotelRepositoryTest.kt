package com.barros9.hotelnow.data.repository

import app.cash.turbine.test
import com.barros9.hotelnow.data.database.mapper.mapToDomainModel
import com.barros9.hotelnow.data.datasource.localdatasource.HotelLocalDataSource
import com.barros9.hotelnow.data.datasource.remotedatasource.HotelRemoteDataSource
import com.barros9.hotelnow.data.mock.DataMock
import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Result.Error
import com.barros9.hotelnow.domain.model.Result.Success
import com.barros9.hotelnow.domain.model.SortType
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
internal class HotelRepositoryTest {

    @MockK
    private lateinit var hotelRemoteDataSource: HotelRemoteDataSource

    @MockK
    private lateinit var hotelLocalDataSource: HotelLocalDataSource

    private lateinit var hotelRepository: HotelRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        hotelRepository = HotelRepositoryImpl(
            remoteDataSource = hotelRemoteDataSource,
            localDataSource = hotelLocalDataSource
        )
    }

    @Test
    fun `call getHotels and return a list of hotel saved locally sorted by name`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.name }
        coEvery { hotelLocalDataSource.getHotelsOrderByName(any()) } returns listOfHotelDatabaseModel

        // When
        hotelRepository.getHotels(SortType.Name, true).test {
            val localData = awaitItem()
            awaitComplete()

            // Then
            coVerify { hotelLocalDataSource.getHotelsOrderByName(any()) }
            assertEquals(listOfHotelDatabaseModel.map { it.mapToDomainModel() }, (localData as Success<List<Hotel>>).data)
        }
    }

    @Test
    fun `call getHotels and return a list of hotel saved locally sorted by stars`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.stars }
        coEvery { hotelLocalDataSource.getHotelsOrderByStars(any()) } returns listOfHotelDatabaseModel

        // When
        hotelRepository.getHotels(SortType.Stars, true).test {
            val localData = awaitItem()
            awaitComplete()

            // Then
            coVerify { hotelLocalDataSource.getHotelsOrderByStars(any()) }
            assertEquals(listOfHotelDatabaseModel.map { it.mapToDomainModel() }, (localData as Success<List<Hotel>>).data)
        }
    }

    @Test
    fun `call getHotels and return a list of hotel saved locally sorted by user ratings`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.userRating }
        coEvery { hotelLocalDataSource.getHotelsOrderByUserRating(any()) } returns listOfHotelDatabaseModel

        // When
        hotelRepository.getHotels(SortType.Rating, true).test {
            val localData = awaitItem()
            awaitComplete()

            // Then
            coVerify { hotelLocalDataSource.getHotelsOrderByUserRating(any()) }
            assertEquals(listOfHotelDatabaseModel.map { it.mapToDomainModel() }, (localData as Success<List<Hotel>>).data)
        }
    }

    @Test
    fun `call getHotels and return a list of hotel saved locally sorted by price`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.price }
        coEvery { hotelLocalDataSource.getHotelsOrderByPrice(any()) } returns listOfHotelDatabaseModel

        // When
        hotelRepository.getHotels(SortType.Price, true).test {
            val localData = awaitItem()
            awaitComplete()

            // Then
            coVerify { hotelLocalDataSource.getHotelsOrderByPrice(any()) }
            assertEquals(listOfHotelDatabaseModel.map { it.mapToDomainModel() }, (localData as Success<List<Hotel>>).data)
        }
    }

    @Test
    fun `call getHotels and return a list of hotel fetched from remote cause local list is empty`() = runTest {
        // Given
        val listOfHotelDatabaseModel = DataMock.listOfHotelDatabaseModel.sortedBy { it.hotelDatabaseModel.name }
        coEvery { hotelLocalDataSource.getHotelsOrderByName(any()) } returnsMany listOf(emptyList(), listOfHotelDatabaseModel)
        coEvery { hotelRemoteDataSource.getHotels() } returns DataMock.listOfHotelNetworkModel
        coEvery { hotelLocalDataSource.insertHotels(any()) } returns Unit
        coEvery { hotelLocalDataSource.insertLocation(any()) } returns Unit
        coEvery { hotelLocalDataSource.insertCheckIn(any()) } returns Unit
        coEvery { hotelLocalDataSource.insertCheckOut(any()) } returns Unit
        coEvery { hotelLocalDataSource.insertContact(any()) } returns Unit

        // When
        hotelRepository.getHotels(SortType.Name, true).test {
            val result = awaitItem()
            awaitComplete()

            // Then
            coVerify { hotelLocalDataSource.getHotelsOrderByName(any()) }
            coVerify { hotelRemoteDataSource.getHotels() }
            coVerify { hotelLocalDataSource.insertHotels(any()) }
            coVerify { hotelLocalDataSource.insertLocation(any()) }
            coVerify { hotelLocalDataSource.insertCheckIn(any()) }
            coVerify { hotelLocalDataSource.insertCheckOut(any()) }
            coVerify { hotelLocalDataSource.insertContact(any()) }
            assertEquals(listOfHotelDatabaseModel.map { it.mapToDomainModel() }, (result as Success<List<Hotel>>).data)
        }
    }

    @Test
    fun `call getHotels and return an error cause local list is empty and remote data throws exception`() = runTest {
        // Given
        coEvery { hotelLocalDataSource.getHotelsOrderByName(any()) } returns emptyList()
        coEvery { hotelRemoteDataSource.getHotels() } throws Exception("Error")

        // When
        hotelRepository.getHotels(SortType.Name, true).test {
            val result = awaitItem()
            awaitComplete()

            // Then
            coVerify { hotelLocalDataSource.getHotelsOrderByName(any()) }
            coVerify { hotelRemoteDataSource.getHotels() }
            assert(result is Error)
        }
    }

    @Test
    fun `call getHotelById and return a Hotel object`() = runTest {
        // Given
        coEvery { hotelLocalDataSource.getHotelById(any()) } returns DataMock.listOfHotelDatabaseModel[2]

        // When
        val result = hotelRepository.getHotelById(2)

        // Then
        coVerify { hotelLocalDataSource.getHotelById(any()) }
        assertEquals(DataMock.listOfHotelDatabaseModel[2].mapToDomainModel(), (result as Success<Hotel>).data)
    }

    @Test
    fun `call getHotelById and return an error`() = runTest {
        // Given
        coEvery { hotelLocalDataSource.getHotelById(any()) } throws Exception("Error")

        // When
        val result = hotelRepository.getHotelById(2)

        // Then
        coVerify { hotelLocalDataSource.getHotelById(any()) }
        assert(result is Error)
    }
}
