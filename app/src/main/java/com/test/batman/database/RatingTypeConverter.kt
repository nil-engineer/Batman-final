package com.test.batman.database

import android.media.Rating
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.batman.model.RatingModel
import java.lang.reflect.Type


class RatingTypeConverter {

    @TypeConverter
    fun stringToMeasurements(json: String?): List<RatingModel>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<RatingModel?>?>() {}.type
        return gson.fromJson<List<RatingModel>>(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<RatingModel?>?): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<RatingModel?>?>() {}.type
        return gson.toJson(list, type)
    }
}