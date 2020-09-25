package com.test.batman.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.batman.Repository.MainRepository
import com.test.batman.model.SearchItemModel

class MainViewModel(private val mainRepository : MainRepository) : ViewModel() {

//    private val mainLiveData = MutableLiveData<SearchResponseModel>()
//    val data: LiveData<SearchResponseModel>
//    get() = data

    fun getMainPage(context: Context, application: Application): MutableLiveData<List<SearchItemModel>> {
        return mainRepository.getMainPage(context, application)
    }

    fun getMainPageOffline(application: Application): LiveData<List<SearchItemModel>>?{
        return mainRepository.getVideos(application)
    }

//    fun insert(searchItemModel: SearchItemModel?) {
//        mainRepository.insert(searchItemModel)
//    }

}