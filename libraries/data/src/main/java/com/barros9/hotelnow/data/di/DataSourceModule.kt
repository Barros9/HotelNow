package com.barros9.hotelnow.data.di

import com.barros9.hotelnow.data.datasource.localdatasource.HotelLocalDataSource
import com.barros9.hotelnow.data.datasource.localdatasource.HotelLocalDataSourceImpl
import com.barros9.hotelnow.data.datasource.remotedatasource.HotelRemoteDataSource
import com.barros9.hotelnow.data.datasource.remotedatasource.HotelRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class DataSourceModule {

    @Provides
    @Singleton
    fun provideHotelRemoteDataSource(remoteDataSource: HotelRemoteDataSourceImpl): HotelRemoteDataSource =
        remoteDataSource

    @Provides
    @Singleton
    fun provideHotelLocalDataSource(localDataSource: HotelLocalDataSourceImpl): HotelLocalDataSource =
        localDataSource
}