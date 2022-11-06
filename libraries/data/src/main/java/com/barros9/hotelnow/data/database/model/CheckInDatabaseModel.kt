package com.barros9.hotelnow.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = HotelDatabaseModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("hotelId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
internal data class CheckInDatabaseModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "hotelId", index = true)
    val hotelId: Long,
    val from: String,
    val to: String
)