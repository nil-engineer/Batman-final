package com.test.batman.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.batman.Repository.DetailRepository
import com.test.batman.Repository.MainRepository
import com.test.batman.model.SearchItemModel
import com.test.batman.model.VideoDetailModel

class DetailViewModel (private val detailRepository: DetailRepository) : ViewModel(){

    fun getDetailPage(context: Context, id: String, application: Application): MutableLiveData<VideoDetailModel>{
        return detailRepository.getDetailPage(context, id, application)
    }

    fun getDetailPageOffline(application: Application, id: String): LiveData<VideoDetailModel>?{
        return detailRepository.getVideoDetail(id, application)
    }
}