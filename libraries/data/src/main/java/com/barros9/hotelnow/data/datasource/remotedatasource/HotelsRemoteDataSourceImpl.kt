package com.barros9.hotelnow.data.datasource.remotedatasource

import com.barros9.hotelnow.data.network.model.HotelNetworkModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class HotelsRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : HotelsRemoteDataSource {
    override suspend fun getHotels() = httpClient.get<List<HotelNetworkModel>>(baseUrl)
}
