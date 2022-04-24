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
}
