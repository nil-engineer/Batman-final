package com.test.batman.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.batman.model.VideoDetailModel

@Dao
interface VideoDetailModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(videoDetailModel: VideoDetailModel)

    @Query("SELECT * from 'videoDetailModel' where imdbID =:id")
    fun getVideoDetail(id: String): LiveData<VideoDetailModel>

    @Delete/*("DELETE FROM 'videoDetailModel' where imdbID=:id")*/
    fun deleteVideo(videoDetailModel: VideoDetailModel)
}