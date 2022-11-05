package com.barros9.hotelnow.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

internal data class HotelDatabaseModelRelations(
    @Embedded
    val hotelDatabaseModel: HotelDatabaseModel,

    @Relation(
        entity = LocationDatabaseModel::class,
        parentColumn = "id",
        entityColumn = "hotelId",
    )
    val location: LocationDatabaseModel,

    @Relation(
        entity = CheckInDatabaseModel::class,
        parentColumn = "id",
        entityColumn = "hotelId",
    )
    val checkIn: CheckInDatabaseModel,

    @Relation(
        entity = CheckOutDatabaseModel::class,
        parentColumn = "id",
        entityColumn = "hotelId",
    )
    val checkOut: CheckOutDatabaseModel,

    @Relation(
        entity = ContactDatabaseModel::class,
        parentColumn = "id",
        entityColumn = "hotelId",
    )
    val contact: ContactDatabaseModel,
)