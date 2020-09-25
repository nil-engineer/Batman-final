package com.test.batman.Repository

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.batman.BatmanAPI
import com.test.batman.database.BatmanRoomDatabase
import com.test.batman.database.VideoDetailModelDao
import com.test.batman.model.VideoDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class DetailRepository(private val api: BatmanAPI) {
    var compositeDisposable = CompositeDisposable()
    var data: MutableLiveData<VideoDetailModel> = MutableLiveData()
    var dbData: LiveData<VideoDetailModel>? = null
    private lateinit var videoDetailModelDao: VideoDetailModelDao

    fun getDetailPage(
        context: Context,
        id: String,
        application: Application
    ): MutableLiveData<VideoDetailModel> {
        var db: BatmanRoomDatabase? = BatmanRoomDatabase.getDatabase(application)
        videoDetailModelDao = db?.videoDetailModelDao()!!
        dbData = videoDetailModelDao.getVideoDetail(id)
        api.getVideoDetail("3e974fca", id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                data.postValue(it)
//                delete()
                insert(it)
            }).let { compositeDisposable.add(it) }
        return data
    }

    private fun insert(videoDetailModel: VideoDetailModel) {
        insertAsyncTask(videoDetailModelDao).execute(videoDetailModel)
    }

    private fun delete() {
        deleteAsyncTask(videoDetailModelDao).execute()
    }

    fun getVideoDetail(id: String, application: Application): LiveData<VideoDetailModel>? {
        var db: BatmanRoomDatabase? = BatmanRoomDatabase.getDatabase(application)
        videoDetailModelDao = db?.videoDetailModelDao()!!
        dbData = videoDetailModelDao.getVideoDetail(id)
        return dbData
    }

    private class insertAsyncTask internal constructor(dao: VideoDetailModelDao) :
        AsyncTask<VideoDetailModel?, Void?, Void?>() {
        private val mAsyncTaskDao: VideoDetailModelDao = dao

        override fun doInBackground(vararg params: VideoDetailModel?): Void? {
            params[0]?.let { mAsyncTaskDao.insert(it) }
            return null
        }
    }

    private class deleteAsyncTask internal constructor(dao: VideoDetailModelDao) :
        AsyncTask<VideoDetailModel?, Void?, Void?>() {
        private val mAsyncTaskDao: VideoDetailModelDao

        init {
            mAsyncTaskDao = dao
        }

        override fun doInBackground(vararg params: VideoDetailModel?): Void? {
            params[0]?.let { mAsyncTaskDao.deleteVideo(it) }
            return null
        }
    }
}