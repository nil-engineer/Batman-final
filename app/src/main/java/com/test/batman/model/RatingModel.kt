package com.test.batman.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
//import com.test.batman.database.RatingTypeConverter
import java.io.Serializable

@Entity(tableName = "ratingModel")
//@TypeConverters(RatingTypeConverter::class)

data class RatingModel(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Source")
    var Source: String,
    @NonNull
    @ColumnInfo(name = "Value")
    var Value: String
) : Serializable
