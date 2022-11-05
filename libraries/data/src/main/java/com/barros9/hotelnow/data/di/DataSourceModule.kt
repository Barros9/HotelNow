package com.barros9.hotelnow.data.di

import com.barros9.hotelnow.data.datasource.localdatasource.HotelsLocalDataSource
import com.barros9.hotelnow.data.datasource.localdatasource.HotelsLocalDataSourceImpl
import com.barros9.hotelnow.data.datasource.remotedatasource.HotelsRemoteDataSource
import com.barros9.hotelnow.data.datasource.remotedatasource.HotelsRemoteDataSourceImpl
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
    fun provideHotelsRemoteDataSource(remoteDataSource: HotelsRemoteDataSourceImpl): HotelsRemoteDataSource =
        remoteDataSource

    @Provides
    @Singleton
    fun provideHotelsLocalDataSource(localDataSource: HotelsLocalDataSourceImpl): HotelsLocalDataSource =
        localDataSource
}