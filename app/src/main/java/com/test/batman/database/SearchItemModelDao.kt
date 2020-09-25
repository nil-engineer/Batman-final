package com.test.batman.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.test.batman.model.SearchItemModel

@Dao
public interface SearchItemModelDao{

    @Insert
    fun insert(searchItemModel: SearchItemModel)

    @Query("SELECT * from 'searchItemModel'")
    fun getVideoList(): LiveData<List<SearchItemModel>>

    @Query("DELETE FROM 'searchItemModel'")
    fun deleteAll()
}