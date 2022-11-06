package com.barros9.hotelnow.data.di

import android.content.Context
import androidx.room.Room
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
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, HotelDatabase::class.java, "hotels.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideDatabaseDao(hotelsDatabase: HotelDatabase) =
        hotelsDatabase.hotelDao()
}
