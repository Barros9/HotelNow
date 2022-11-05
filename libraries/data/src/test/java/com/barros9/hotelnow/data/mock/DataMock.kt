package com.barros9.hotelnow.data.mock

import com.barros9.hotelnow.data.database.model.CheckInDatabaseModel
import com.barros9.hotelnow.data.database.model.CheckOutDatabaseModel
import com.barros9.hotelnow.data.database.model.ContactDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModelRelations
import com.barros9.hotelnow.data.database.model.LocationDatabaseModel
import com.barros9.hotelnow.data.network.model.CheckInNetworkModel
import com.barros9.hotelnow.data.network.model.CheckOutNetworkModel
import com.barros9.hotelnow.data.network.model.ContactNetworkModel
import com.barros9.hotelnow.data.network.model.HotelNetworkModel
import com.barros9.hotelnow.data.network.model.LocationNetworkModel
import io.ktor.utils.io.ByteReadChannel

internal object DataMock {
    val listOfHotelDatabaseModel = listOf(
        HotelDatabaseModelRelations(
            hotelDatabaseModel = HotelDatabaseModel(
                id = 1,
                name = "Hotel_1",
                stars = 4,
                gallery = emptyList(),
                userRating = 7.0,
                price = 89.0,
                currency = "Euro"
            ),
            checkIn = CheckInDatabaseModel(
                id = 1,
                hotelId = 1,
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOutDatabaseModel(
                id = 1,
                hotelId = 1,
                from = "24:00",
                to = "10:00",
            ),
            location = LocationDatabaseModel(
                id = 1,
                hotelId = 1,
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = ContactDatabaseModel(
                id = 1,
                hotelId = 1,
                phoneNumber = "123456789",
                email = "email@email.com"
            )
        ),
        HotelDatabaseModelRelations(
            HotelDatabaseModel(
                id = 2,
                name = "Hotel_2",
                stars = 1,
                gallery = emptyList(),
                userRating = 6.0,
                price = 36.0,
                currency = "Euro"
            ),
            checkIn = CheckInDatabaseModel(
                id = 2,
                hotelId = 2,
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOutDatabaseModel(
                id = 2,
                hotelId = 2,
                from = "24:00",
                to = "10:00",
            ),
            location = LocationDatabaseModel(
                id = 2,
                hotelId = 2,
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = ContactDatabaseModel(
                id = 2,
                hotelId = 2,
                phoneNumber = "123456789",
                email = "email@email.com"
            )
        ),
        HotelDatabaseModelRelations(
            HotelDatabaseModel(
                id = 3,
                name = "Hotel_3",
                stars = 5,
                gallery = emptyList(),
                userRating = 9.0,
                price = 196.0,
                currency = "Euro"
            ),
            checkIn = CheckInDatabaseModel(
                id = 3,
                hotelId = 3,
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOutDatabaseModel(
                id = 3,
                hotelId = 3,
                from = "24:00",
                to = "10:00",
            ),
            location = LocationDatabaseModel(
                id = 3,
                hotelId = 3,
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = ContactDatabaseModel(
                id = 3,
                hotelId = 3,
                phoneNumber = "123456789",
                email = "email@email.com"
            )
        )
    )

    val listOfHotelNetworkModel = listOf(
        HotelNetworkModel(
            id = 1,
            name = "Hotel_1",
            stars = 4,
            checkIn = CheckInNetworkModel(
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOutNetworkModel(
                from = "24:00",
                to = "10:00",
            ),
            location = LocationNetworkModel(
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = ContactNetworkModel(
                phoneNumber = "123456789",
                email = "email@email.com"
            ),
            gallery = emptyList(),
            userRating = 7.0,
            price = 89.0,
            currency = "Euro"
        ),
        HotelNetworkModel(
            id = 2,
            name = "Hotel_2",
            stars = 1,
            checkIn = CheckInNetworkModel(
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOutNetworkModel(
                from = "24:00",
                to = "10:00",
            ),
            location = LocationNetworkModel(
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = ContactNetworkModel(
                phoneNumber = "123456789",
                email = "email@email.com"
            ),
            gallery = emptyList(),
            userRating = 6.0,
            price = 36.0,
            currency = "Euro"
        ),
        HotelNetworkModel(
            id = 3,
            name = "Hotel_3",
            stars = 5,
            checkIn = CheckInNetworkModel(
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOutNetworkModel(
                from = "24:00",
                to = "10:00",
            ),
            location = LocationNetworkModel(
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = ContactNetworkModel(
                phoneNumber = "123456789",
                email = "email@email.com"
            ),
            gallery = emptyList(),
            userRating = 9.0,
            price = 196.0,
            currency = "Euro"
        )
    )

    val listOfHotelNetworkJson = ByteReadChannel(
        """
       [
           {
              "id":12321,
              "name":"Park Plaza London Waterloo",
              "location":{
                 "address":"6 Hercules Road",
                 "city":"London",
                 "latitude":51.49845,
                 "longitude":-0.11343
              },
              "stars":4,
              "checkIn":{
                 "from":"14:00",
                 "to":"20:00"
              },
              "checkOut":{
                 "from":"07:00",
                 "to":"10:00"
              },
              "contact":{
                 "phoneNumber":"+39 24322342",
                 "email":"park_plaza@gmail.com"
              },
              "gallery":[
                 "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1499779963/Swindon_yjsrwz.jpg"
              ],
              "userRating":9.8,
              "price":120,
              "currency":"EUR"
           },
           {
              "id":12322,
              "name":"Belgrave House Hotel",
              "location":{
                 "address":"28-32 Belgrave Road Victoria",
                 "city":"London",
                 "latitude":51.49241,
                 "longitude":-0.14283
              },
              "stars":4,
              "checkIn":{
                 "from":"12:00",
                 "to":"20:00"
              },
              "checkOut":{
                 "from":"07:00",
                 "to":"10:00"
              },
              "contact":{
                 "phoneNumber":"+44 5477432",
                 "email":"belgrave@gmail.com"
              },
              "gallery":[
                 "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1602584265/g8wzbaqahffteguzal5d.jpg",
                 "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1602584267/zvpg4qui6pqp6t9lzmfz.jpg",
                 "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1602584269/storgpwbactbomqnevn5.jpg",
                 "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1491054438/Living_Area_26_7_mdxypk.jpg",
                 "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1491054438/Living_Area_25_6_rkyqhx.jpg",
                 "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1491054437/Guestroom_31_9_s2va2b.jpg",
                 "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1491054437/Guestroom_32_4_rpuq3s.jpg"
              ],
              "userRating":7.8,
              "price":100,
              "currency":"EUR"
           }
        ]""".trimIndent()
    )

    val listOfHotelNetworkJsonToObject = listOf(
        HotelNetworkModel(
            id = 12321,
            name = "Park Plaza London Waterloo",
            stars = 4,
            checkIn = CheckInNetworkModel(
                from = "14:00",
                to = "20:00",
            ),
            checkOut = CheckOutNetworkModel(
                from = "07:00",
                to = "10:00",
            ),
            location = LocationNetworkModel(
                address = "6 Hercules Road",
                city = "London",
                latitude = 51.49845,
                longitude = -0.11343
            ),
            contact = ContactNetworkModel(
                phoneNumber = "+39 24322342",
                email = "park_plaza@gmail.com"
            ),
            gallery = listOf(
                "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1499779963/Swindon_yjsrwz.jpg"
            ),
            userRating = 9.8,
            price = 120.0,
            currency = "EUR"
        ),
        HotelNetworkModel(
            id = 12322,
            name = "Belgrave House Hotel",
            stars = 4,
            checkIn = CheckInNetworkModel(
                from = "12:00",
                to = "20:00",
            ),
            checkOut = CheckOutNetworkModel(
                from = "07:00",
                to = "10:00",
            ),
            location = LocationNetworkModel(
                address = "28-32 Belgrave Road Victoria",
                city = "London",
                latitude = 51.49241,
                longitude = -0.14283
            ),
            contact = ContactNetworkModel(
                phoneNumber = "+44 5477432",
                email = "belgrave@gmail.com"
            ),
            gallery = listOf(
                "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1602584265/g8wzbaqahffteguzal5d.jpg",
                "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1602584267/zvpg4qui6pqp6t9lzmfz.jpg",
                "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1602584269/storgpwbactbomqnevn5.jpg",
                "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1491054438/Living_Area_26_7_mdxypk.jpg",
                "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1491054438/Living_Area_25_6_rkyqhx.jpg",
                "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1491054437/Guestroom_31_9_s2va2b.jpg",
                "https://res.cloudinary.com/lastminute/image/upload/t_OSE_redes_item_view/v1491054437/Guestroom_32_4_rpuq3s.jpg"
            ),
            userRating = 7.8,
            price = 100.0,
            currency = "EUR"
        )
    )
}