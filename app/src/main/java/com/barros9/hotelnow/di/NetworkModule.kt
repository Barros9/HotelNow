package com.barros9.hotelnow.di

import com.barros9.hotelnow.data.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return NetworkService.createHttpClient()
    }

}