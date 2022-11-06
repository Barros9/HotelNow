package com.barros9.hotelnow.presentation.mock


import com.barros9.hotelnow.domain.model.CheckIn
import com.barros9.hotelnow.domain.model.CheckOut
import com.barros9.hotelnow.domain.model.Contact
import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Location

internal object PresentationMock {

    val listOfHotel = listOf(
        Hotel(
            id = 1,
            name = "Hotel_1",
            stars = 4,
            checkIn = CheckIn(
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOut(
                from = "24:00",
                to = "10:00",
            ),
            location = Location(
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = Contact(
                phoneNumber = "123456789",
                email = "email@email.com"
            ),
            gallery = emptyList(),
            userRating = 7.0,
            price = 89.0,
            currency = "Euro"
        ),
        Hotel(
            id = 2,
            name = "Hotel_2",
            stars = 1,
            checkIn = CheckIn(
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOut(
                from = "24:00",
                to = "10:00",
            ),
            location = Location(
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = Contact(
                phoneNumber = "123456789",
                email = "email@email.com"
            ),
            gallery = emptyList(),
            userRating = 6.0,
            price = 36.0,
            currency = "Euro"
        ),
        Hotel(
            id = 3,
            name = "Hotel_3",
            stars = 5,
            checkIn = CheckIn(
                from = "14:00",
                to = "24:00",
            ),
            checkOut = CheckOut(
                from = "24:00",
                to = "10:00",
            ),
            location = Location(
                address = "address",
                city = "city",
                latitude = 0.0,
                longitude = 0.0
            ),
            contact = Contact(
                phoneNumber = "123456789",
                email = "email@email.com"
            ),
            gallery = emptyList(),
            userRating = 9.0,
            price = 196.0,
            currency = "Euro"
        )
    )

}