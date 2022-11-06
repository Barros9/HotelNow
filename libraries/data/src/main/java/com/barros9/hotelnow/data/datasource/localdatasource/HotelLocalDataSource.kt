package com.barros9.hotelnow.data.datasource.localdatasource

import com.barros9.hotelnow.data.database.model.CheckInDatabaseModel
import com.barros9.hotelnow.data.database.model.CheckOutDatabaseModel
import com.barros9.hotelnow.data.database.model.ContactDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModelRelations
import com.barros9.hotelnow.data.database.model.LocationDatabaseModel

internal interface HotelLocalDataSource {
    suspend fun getHotels(): List<HotelDatabaseModelRelations>
    suspend fun getHotelById(hotelId: Long): HotelDatabaseModelRelations
    suspend fun getHotelsOrderByName(isAscending: Boolean): List<HotelDatabaseModelRelations>
    suspend fun getHotelsOrderByStars(isAscending: Boolean): List<HotelDatabaseModelRelations>
    suspend fun getHotelsOrderByUserRating(isAscending: Boolean): List<HotelDatabaseModelRelations>
    suspend fun getHotelsOrderByPrice(isAscending: Boolean): List<HotelDatabaseModelRelations>
    suspend fun insertHotels(hotels: List<HotelDatabaseModel>)
    suspend fun insertLocation(locationDatabaseModel: LocationDatabaseModel)
    suspend fun insertCheckIn(checkInDatabaseModel: CheckInDatabaseModel)
    suspend fun insertCheckOut(checkOutDatabaseModel: CheckOutDatabaseModel)
    suspend fun insertContact(contactDatabaseModel: ContactDatabaseModel)
}