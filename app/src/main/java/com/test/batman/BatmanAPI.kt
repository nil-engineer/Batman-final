package com.test.batman

import com.test.batman.model.SearchResponseModel
import com.test.batman.model.VideoDetailModel
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface BatmanAPI {

    @GET("https://www.omdbapi.com/")
    fun getBatmanVideos(@Query("apikey") apikey: String, @Query("s") s: String): Observable<SearchResponseModel>

    @GET("https://www.omdbapi.com/")
    fun getVideoDetail(@Query("apikey") apikey: String, @Query("i") i: String): Observable<VideoDetailModel>
}