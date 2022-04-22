package com.barros9.hotelnow.data.hotels

import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.data.getResult
import com.barros9.hotelnow.data.hotels.remotedatasource.HotelsRemoteDataSourceImpl
import com.barros9.hotelnow.domain.models.Hotel
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(
    private val remoteDataSource: HotelsRemoteDataSourceImpl
) : HotelsRepository {
    override suspend fun getHotels(): Result<List<Hotel>> = getResult {
        remoteDataSource.getHotels()
    }
}
