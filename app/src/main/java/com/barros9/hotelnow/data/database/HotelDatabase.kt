package com.barros9.hotelnow.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barros9.hotelnow.domain.models.Hotel

@Database(entities = [Hotel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun hotelDao(): HotelDao

    companion object {
        @Volatile
        private var instance: HotelDatabase? = null

        fun getInstance(context: Context): HotelDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): HotelDatabase {
            return Room.databaseBuilder(context, HotelDatabase::class.java, "hotels.db").build()
        }
    }
}

