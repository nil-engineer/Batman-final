package com.test.batman.Repository

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.batman.BatmanAPI
import com.test.batman.database.BatmanRoomDatabase
import com.test.batman.database.SearchItemModelDao
import com.test.batman.Utilities.CheckNetwork
import com.test.batman.model.SearchItemModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainRepository(private val api: BatmanAPI) {
    var compositeDisposable = CompositeDisposable()
    var data: MutableLiveData<List<SearchItemModel>> = MutableLiveData()
    var dbData: LiveData<List<SearchItemModel>>? = null
    private lateinit var searchItemModelDao: SearchItemModelDao


    fun getMainPage(
        context: Context,
        application: Application
    ): MutableLiveData<List<SearchItemModel>> {

        var db: BatmanRoomDatabase? = BatmanRoomDatabase.getDatabase(application)
        searchItemModelDao = db?.searchItemModelDao()!!
        dbData = searchItemModelDao.getVideoList()

        api.getBatmanVideos("3e974fca", "batman")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                data.postValue(it.searchItemList)
                delete()
                for (searchItem: SearchItemModel in it.searchItemList) {
                    insert(searchItem)
                }
            }).let { compositeDisposable.add(it) }

        return data
    }

    fun getVideos(application: Application): LiveData<List<SearchItemModel>>? {
        var db: BatmanRoomDatabase? = BatmanRoomDatabase.getDatabase(application)
        searchItemModelDao = db?.searchItemModelDao()!!
        dbData = searchItemModelDao.getVideoList()
        return dbData
    }

    private fun insert(searchItemModel: SearchItemModel?) {
        insertAsyncTask(searchItemModelDao).execute(searchItemModel)
//        searchItemModelDao.let { insertAsyncTask(it).execute(searchItemModel) }
    }

    fun delete() {
        deleteAsyncTask(searchItemModelDao).execute()
    }

    private class insertAsyncTask internal constructor(dao: SearchItemModelDao) :
        AsyncTask<SearchItemModel?, Void?, Void?>() {
        private val mAsyncTaskDao: SearchItemModelDao = dao

        override fun doInBackground(vararg params: SearchItemModel?): Void? {
            params[0]?.let { mAsyncTaskDao.insert(it) }
            return null
        }
    }

    private class deleteAsyncTask internal constructor(dao: SearchItemModelDao) :
        AsyncTask<SearchItemModel?, Void?, Void?>() {
        private val mAsyncTaskDao: SearchItemModelDao

        init {
            mAsyncTaskDao = dao
        }


        override fun doInBackground(vararg params: SearchItemModel?): Void? {
            mAsyncTaskDao.deleteAll()
            return null
        }
    }
}