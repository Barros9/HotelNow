package com.barros9.hotelnow.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barros9.hotelnow.data.database.model.ContactDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModel
import com.barros9.hotelnow.data.database.model.LocationDatabaseModel
import com.barros9.hotelnow.data.database.model.RangeHoursDatabaseModel

@Database(
    entities = [
        HotelDatabaseModel::class,
        LocationDatabaseModel::class,
        RangeHoursDatabaseModel::class,
        ContactDatabaseModel::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
internal abstract class HotelDatabase : RoomDatabase() {

    abstract fun hotelDao(): HotelDao

    companion object {
        @Volatile
        private var instance: HotelDatabase? = null

        fun getInstance(context: Context): HotelDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): HotelDatabase =
            Room.databaseBuilder(context, HotelDatabase::class.java, "hotels.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
