package com.barros9.hotelnow.data.repository

import com.barros9.hotelnow.data.database.mapper.mapFromDomainModel
import com.barros9.hotelnow.data.database.mapper.mapToDomainModel
import com.barros9.hotelnow.data.database.model.HotelDatabaseModelRelations
import com.barros9.hotelnow.data.datasource.localdatasource.HotelsLocalDataSource
import com.barros9.hotelnow.data.datasource.remotedatasource.HotelsRemoteDataSource
import com.barros9.hotelnow.data.network.mapper.mapToDomainModel
import com.barros9.hotelnow.data.network.model.HotelNetworkModel
import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.Result
import com.barros9.hotelnow.domain.model.SortType
import com.barros9.hotelnow.domain.model.getResult
import com.barros9.hotelnow.domain.repository.HotelRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class HotelRepositoryImpl @Inject constructor(
    private val remoteDataSource: HotelsRemoteDataSource,
    private val localDataSource: HotelsLocalDataSource
) : HotelRepository {

    override suspend fun getHotels(sortType: SortType, isAsc: Boolean): Flow<Result<List<Hotel>>> =
        singleSourceOfTruthStrategy(
            readLocalData = {
                val localData = when (sortType) {
                    SortType.Stars -> localDataSource.getHotelsOrderByStars(isAsc)
                    SortType.Rating -> localDataSource.getHotelsOrderByUserRating(isAsc)
                    SortType.Price -> localDataSource.getHotelsOrderByPrice(isAsc)
                    SortType.Name -> localDataSource.getHotelsOrderByName(isAsc)
                }
                localData.map(HotelDatabaseModelRelations::mapToDomainModel)
            },
            readRemoteData = {
                remoteDataSource.getHotels().map(HotelNetworkModel::mapToDomainModel)
            },
            saveLocalData = { hotels ->
                localDataSource.insertHotels(hotels.map(Hotel::mapFromDomainModel))
                hotels.forEach { hotel ->
                    localDataSource.insertLocation(hotel.location.mapFromDomainModel(hotel.id))
                    localDataSource.insertRangeHours(hotel.checkIn.mapFromDomainModel(hotel.id))
                    localDataSource.insertRangeHours(hotel.checkOut.mapFromDomainModel(hotel.id))
                    localDataSource.insertContact(hotel.contact.mapFromDomainModel(hotel.id))
                }
            }
        )

    override suspend fun getHotelById(hotelId: Long): Result<Hotel> =
        getResult { localDataSource.getHotelById(hotelId).mapToDomainModel() }
}
