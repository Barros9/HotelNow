package com.barros9.hotelnow.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import kotlinx.serialization.json.Json

internal object NetworkService {
    fun createHttpClient(): HttpClient {
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
}

