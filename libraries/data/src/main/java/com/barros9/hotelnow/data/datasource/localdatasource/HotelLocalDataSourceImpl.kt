package com.barros9.hotelnow.data.datasource.localdatasource

import com.barros9.hotelnow.data.database.HotelDao
import com.barros9.hotelnow.data.database.model.CheckInDatabaseModel
import com.barros9.hotelnow.data.database.model.CheckOutDatabaseModel
import com.barros9.hotelnow.data.database.model.ContactDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModelRelations
import com.barros9.hotelnow.data.database.model.LocationDatabaseModel
import javax.inject.Inject

internal class HotelLocalDataSourceImpl @Inject constructor(
    private val hotelDao: HotelDao
) : HotelLocalDataSource {
    override suspend fun getHotels(): List<HotelDatabaseModelRelations> =
        hotelDao.getHotels()

    override suspend fun getHotelById(hotelId: Long): HotelDatabaseModelRelations =
        hotelDao.getHotelById(hotelId)

    override suspend fun getHotelsOrderByName(isAscending: Boolean): List<HotelDatabaseModelRelations> =
        hotelDao.getHotelsOrderByName(isAscending)

    override suspend fun getHotelsOrderByStars(isAscending: Boolean): List<HotelDatabaseModelRelations> =
        hotelDao.getHotelsOrderByStars(isAscending)

    override suspend fun getHotelsOrderByUserRating(isAscending: Boolean): List<HotelDatabaseModelRelations> =
        hotelDao.getHotelsOrderByUserRating(isAscending)

    override suspend fun getHotelsOrderByPrice(isAscending: Boolean): List<HotelDatabaseModelRelations> =
        hotelDao.getHotelsOrderByPrice(isAscending)

    override suspend fun insertHotels(hotels: List<HotelDatabaseModel>) =
        hotelDao.insertHotels(hotels)

    override suspend fun insertLocation(locationDatabaseModel: LocationDatabaseModel) =
        hotelDao.insertLocation(locationDatabaseModel)

    override suspend fun insertCheckIn(checkInDatabaseModel: CheckInDatabaseModel) =
        hotelDao.insertCheckIn(checkInDatabaseModel)

    override suspend fun insertCheckOut(checkOutDatabaseModel: CheckOutDatabaseModel) =
        hotelDao.insertCheckOut(checkOutDatabaseModel)

    override suspend fun insertContact(contactDatabaseModel: ContactDatabaseModel) =
        hotelDao.insertContact(contactDatabaseModel)
}