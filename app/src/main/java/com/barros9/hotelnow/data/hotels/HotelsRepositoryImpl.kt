package com.barros9.hotelnow.data.hotels

import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.data.getResult
import com.barros9.hotelnow.data.hotels.localdatasource.HotelsLocalDataSource
import com.barros9.hotelnow.data.hotels.remotedatasource.HotelsRemoteDataSource
import com.barros9.hotelnow.domain.models.Hotel
import com.barros9.hotelnow.domain.models.SortType
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(
    private val remoteDataSource: HotelsRemoteDataSource,
    private val localDataSource: HotelsLocalDataSource
) : HotelsRepository {
    override suspend fun getHotels(): Result<List<Hotel>> = getResult {
        val hotels = remoteDataSource.getHotels()
        localDataSource.insertHotels(hotels)
        localDataSource.getHotels()
    }

    override suspend fun getHotelsOrderBy(sortType: SortType, isAsc: Boolean) = getResult {
        when (sortType) {
            SortType.Stars -> localDataSource.getHotelsOrderByStars(isAsc)
            SortType.UserRating -> localDataSource.getHotelsOrderByUserRating(isAsc)
            SortType.Price -> localDataSource.getHotelsOrderByPrice(isAsc)
            SortType.None -> localDataSource.getHotels()
        }
    }
}
