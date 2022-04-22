package com.barros9.hotelnow.data.hotels.remotedatasource

import com.barros9.hotelnow.BuildConfig
import com.barros9.hotelnow.domain.models.Hotel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class HotelsRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : HotelsRemoteDataSource {
    override suspend fun getHotels() = httpClient.get<List<Hotel>>(BuildConfig.BASE_URL)
}
