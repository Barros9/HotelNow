package com.barros9.hotelnow.data.network

import com.barros9.hotelnow.data.network.model.HotelNetworkModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class HotelNetwork @Inject constructor(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : HotelApi {
    override suspend fun getHotels() = httpClient.get<List<HotelNetworkModel>>(baseUrl)
}