package com.test.batman.DaggerModule

import com.test.batman.Repository.DetailRepository
import com.test.batman.Repository.MainRepository
import com.test.batman.ViewModel.DetailViewModelFactory
import com.test.batman.ViewModel.MainViewModel
import com.test.batman.ViewModel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesMainViewModelFactory(mainRepository: MainRepository): MainViewModelFactory{
        return MainViewModelFactory(mainRepository)
    }

    @Provides
    fun providesDetailViewModelFactory(detailRepository: DetailRepository): DetailViewModelFactory{
        return DetailViewModelFactory(detailRepository)
    }
}