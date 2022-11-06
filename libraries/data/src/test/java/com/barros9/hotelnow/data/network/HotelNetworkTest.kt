package com.barros9.hotelnow.data.network

import com.barros9.hotelnow.data.mock.DataMock
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class HotelNetworkTest {

    private lateinit var mockEngine: MockEngine
    private lateinit var httpClient: HttpClient
    private lateinit var hotelNetwork: HotelNetwork

    @Before
    fun setup() {
        mockEngine = MockEngine {
            respond(
                content = DataMock.listOfHotelNetworkJson,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        httpClient = HttpClient(mockEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
        hotelNetwork = HotelNetwork(httpClient, "baseUrl")
    }

    @After
    fun tearDown() {
        httpClient.close()
        mockEngine.close()
    }

    @Test
    fun `should fetch hotel list correctly giving success response`() = runBlocking {
        // Given

        // When
        val response = hotelNetwork.getHotels()

        // Then
        assertEquals(DataMock.listOfHotelNetworkJsonToObject, response)
    }
}