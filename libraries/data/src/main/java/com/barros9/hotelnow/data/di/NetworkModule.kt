package com.barros9.hotelnow.data.di

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
    fun provideBaseUrl(): String = "https://run.mocky.io/v3/eef3c24d-5bfd-4881-9af7-0b404ce09507"

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient = NetworkService.createHttpClient()
}