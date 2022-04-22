package com.barros9.hotelnow.di

import com.barros9.hotelnow.data.hotels.HotelsRepository
import com.barros9.hotelnow.data.hotels.HotelsRepositoryImpl
import com.barros9.hotelnow.data.hotels.remotedatasource.HotelsRemoteDataSource
import com.barros9.hotelnow.data.hotels.remotedatasource.HotelsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HotelsModule {

    @Provides
    @Singleton
    fun provideHotelsRemoteDataSource(remoteDataSource: HotelsRemoteDataSourceImpl): HotelsRemoteDataSource {
        return remoteDataSource
    }

    @Provides
    @Singleton
    fun provideHotelsRepository(repository: HotelsRepositoryImpl): HotelsRepository {
        return repository
    }
}