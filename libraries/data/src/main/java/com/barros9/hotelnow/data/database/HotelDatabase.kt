package com.barros9.hotelnow.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barros9.hotelnow.data.database.model.CheckInDatabaseModel
import com.barros9.hotelnow.data.database.model.CheckOutDatabaseModel
import com.barros9.hotelnow.data.database.model.ContactDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModel
import com.barros9.hotelnow.data.database.model.LocationDatabaseModel

@Database(
    entities = [
        HotelDatabaseModel::class,
        LocationDatabaseModel::class,
        CheckInDatabaseModel::class,
        CheckOutDatabaseModel::class,
        ContactDatabaseModel::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
internal abstract class HotelDatabase : RoomDatabase() {
    abstract fun hotelDao(): HotelDao
}
