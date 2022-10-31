package com.barros9.hotelnow.di

import android.content.Context
import com.barros9.hotelnow.data.database.HotelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = HotelDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideDatabaseDao(hotelsDatabase: HotelDatabase) = hotelsDatabase.hotelDao()
}
