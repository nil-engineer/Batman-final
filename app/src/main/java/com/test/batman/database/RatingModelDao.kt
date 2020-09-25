package com.test.batman.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.test.batman.model.RatingModel
import com.test.batman.model.SearchItemModel

@Dao
interface RatingModelDao {

    @Insert
    fun insert(ratingModel: RatingModel)

    @Query("SELECT * from 'ratingModel' ORDER BY 'id' ASC")
    fun getRatingList(): LiveData<List<RatingModel?>?>?
}