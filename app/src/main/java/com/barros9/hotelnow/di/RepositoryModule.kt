package com.barros9.hotelnow.di

import com.barros9.hotelnow.data.repository.HotelRepositoryImpl
import com.barros9.hotelnow.domain.repository.HotelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class RepositoryModule {

    @Provides
    @Singleton
    fun provideHotelRepository(repository: HotelRepositoryImpl): HotelRepository {
        return repository
    }
}