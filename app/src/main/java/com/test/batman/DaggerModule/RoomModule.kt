package com.test.batman.DaggerModule

import android.app.Application
import androidx.room.Room
import com.test.batman.database.BatmanRoomDatabase
import com.test.batman.database.RatingModelDao
import com.test.batman.database.SearchItemModelDao
import com.test.batman.database.VideoDetailModelDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module

class RoomModule {
    private lateinit var batmanRoomDatabase: BatmanRoomDatabase

    fun DBModule(mApplication: Application?) {
        batmanRoomDatabase =
            Room.databaseBuilder(mApplication!!, BatmanRoomDatabase::class.java, "batman.db")
                .build()
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): BatmanRoomDatabase? {
        return batmanRoomDatabase
    }

    @Singleton
    @Provides
    fun providesSearchItemModelDao(batmanRoomDatabase: BatmanRoomDatabase): SearchItemModelDao? {
        return batmanRoomDatabase.searchItemModelDao()
    }

    @Singleton
    @Provides
    fun providesVideoDetailModelDao(batmanRoomDatabase: BatmanRoomDatabase): VideoDetailModelDao? {
        return batmanRoomDatabase.videoDetailModelDao()
    }

    @Singleton
    @Provides
    fun providesRatingModelDao(batmanRoomDatabase: BatmanRoomDatabase): RatingModelDao? {
        return batmanRoomDatabase.ratingModelDao()
    }
}