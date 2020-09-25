package com.test.batman.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.batman.Repository.DetailRepository
import com.test.batman.Repository.MainRepository
import javax.inject.Inject

class DetailViewModelFactory  @Inject constructor(private val detailRepository: DetailRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(detailRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}