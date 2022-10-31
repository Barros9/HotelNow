package com.barros9.hotelnow.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barros9.hotelnow.data.database.model.ContactDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModelRelations
import com.barros9.hotelnow.data.database.model.LocationDatabaseModel
import com.barros9.hotelnow.data.database.model.RangeHoursDatabaseModel

@Dao
internal interface HotelDao {

    @Query("SELECT * FROM hotels")
    suspend fun getHotels(): List<HotelDatabaseModelRelations>

    @Query("SELECT * FROM hotels WHERE id LIKE :hotelId")
    suspend fun getHotelById(hotelId: Long): HotelDatabaseModelRelations

    @Query("SELECT * FROM hotels ORDER BY CASE WHEN :isAsc = 1 THEN name END ASC, CASE WHEN :isAsc = 0 THEN name END DESC")
    suspend fun getHotelsOrderByName(isAsc: Boolean): List<HotelDatabaseModelRelations>

    @Query("SELECT * FROM hotels ORDER BY CASE WHEN :isAsc = 1 THEN stars END ASC, CASE WHEN :isAsc = 0 THEN stars END DESC")
    suspend fun getHotelsOrderByStars(isAsc: Boolean): List<HotelDatabaseModelRelations>

    @Query("SELECT * FROM hotels ORDER BY CASE WHEN :isAsc = 1 THEN userRating END ASC, CASE WHEN :isAsc = 0 THEN userRating END DESC")
    suspend fun getHotelsOrderByUserRating(isAsc: Boolean): List<HotelDatabaseModelRelations>

    @Query("SELECT * FROM hotels ORDER BY CASE WHEN :isAsc = 1 THEN price END ASC, CASE WHEN :isAsc = 0 THEN price END DESC")
    suspend fun getHotelsOrderByPrice(isAsc: Boolean): List<HotelDatabaseModelRelations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotels(hotels: List<HotelDatabaseModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(locationDatabaseModel: LocationDatabaseModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRangeHours(rangeHoursDatabaseModel: RangeHoursDatabaseModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contactDatabaseModel: ContactDatabaseModel)

}
