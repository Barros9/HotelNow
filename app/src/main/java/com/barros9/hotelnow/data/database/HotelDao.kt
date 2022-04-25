package com.barros9.hotelnow.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barros9.hotelnow.domain.models.Hotel

@Dao
interface HotelDao {

    @Query("SELECT * FROM hotels")
    suspend fun getHotels(): List<Hotel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotels(hotels: List<Hotel>)

    @Query("SELECT * FROM hotels ORDER BY CASE WHEN :isAsc = 1 THEN stars END ASC, CASE WHEN :isAsc = 0 THEN stars END DESC")
    suspend fun getHotelsOrderByStars(isAsc: Boolean): List<Hotel>

    @Query("SELECT * FROM hotels ORDER BY CASE WHEN :isAsc = 1 THEN userRating END ASC, CASE WHEN :isAsc = 0 THEN userRating END DESC")
    suspend fun getHotelsOrderByUserRating(isAsc: Boolean): List<Hotel>

    @Query("SELECT * FROM hotels ORDER BY CASE WHEN :isAsc = 1 THEN price END ASC, CASE WHEN :isAsc = 0 THEN price END DESC")
    suspend fun getHotelsOrderByPrice(isAsc: Boolean): List<Hotel>
}
