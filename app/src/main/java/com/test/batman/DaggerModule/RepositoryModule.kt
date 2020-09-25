package com.test.batman.DaggerModule

import com.test.batman.BatmanAPI
import com.test.batman.Repository.DetailRepository
import com.test.batman.Repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesMainRepository(api: BatmanAPI): MainRepository{
        return MainRepository(api)
    }

    @Provides
    @Singleton
    fun providesDetailRepository(api: BatmanAPI): DetailRepository{
        return DetailRepository(api)
    }
}