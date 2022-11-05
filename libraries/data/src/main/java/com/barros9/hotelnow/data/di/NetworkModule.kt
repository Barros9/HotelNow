package com.barros9.hotelnow.data.di

import com.barros9.hotelnow.data.network.CustomHttpLogger
import com.barros9.hotelnow.data.network.HotelApi
import com.barros9.hotelnow.data.network.HotelNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String =
        "https://run.mocky.io/v3/eef3c24d-5bfd-4881-9af7-0b404ce09507"

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        val client = HttpClient(Android) {
            engine {
                connectTimeout = 30_000
                socketTimeout = 30_000
            }
            install(Logging) {
                logger = CustomHttpLogger()
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }

        client.sendPipeline.intercept(HttpSendPipeline.State) {
            context.headers.append("AppVersion", "BuildConfig.VERSION_NAME")
        }

        return client
    }

    @Singleton
    @Provides
    fun provideHotelApi(hotelNetwork: HotelNetwork): HotelApi =
        hotelNetwork
}