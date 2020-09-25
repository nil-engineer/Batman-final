package com.test.batman.model

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName
//import com.test.batman.database.RatingTypeConverter
import java.io.Serializable

@Entity(tableName = "videoDetailModel")
//@TypeConverters(RatingTypeConverter::class)

data class VideoDetailModel(
    @NonNull
    @ColumnInfo(name = "Title")
    var Title: String,
    @NonNull
    @ColumnInfo(name = "Year")
    var Year: String,
    @NonNull
    @ColumnInfo(name = "Rated")
    var Rated: String,
    @NonNull
    @ColumnInfo(name = "Released")
    var Released: String,
    @NonNull
    @ColumnInfo(name = "Runtime")
    var Runtime: String,
    @NonNull
    @ColumnInfo(name = "Genre")
    var Genre: String,
    @NonNull
    @ColumnInfo(name = "Director")
    var Director: String,
    @NonNull
    @ColumnInfo(name = "Writer")
    var Writer: String,
    @NonNull
    @ColumnInfo(name = "Actors")
    var Actors: String,
    @NonNull
    @ColumnInfo(name = "Plot")
    var Plot: String,
    @NonNull
    @ColumnInfo(name = "Language")
    var Language: String,
    @NonNull
    @ColumnInfo(name = "Country")
    var Country: String,
    @NonNull
    @ColumnInfo(name = "Awards")
    var Awards: String,
    @NonNull
    @ColumnInfo(name = "Poster")
    var Poster: String,
    @SerializedName("Ratings")
    @ColumnInfo(name = "rating")
//    @TypeConverters(RatingTypeConverter::class)
//    @Ignore
    var ratingList: List<RatingModel>,
    @NonNull
    @ColumnInfo(name = "Metascore")
    var Metascore: String,
    @NonNull
    @ColumnInfo(name = "imdbRating")
    var imdbRating: String,
    @NonNull
    @ColumnInfo(name = "imdbVotes")
    var imdbVotes: String,
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "imdbID")
    var imdbID: String,
    @NonNull
    @ColumnInfo(name = "Type")
    var Type: String,
    @NonNull
    @ColumnInfo(name = "DVD")
    var DVD: String,
    @NonNull
    @ColumnInfo(name = "BoxOffice")
    var BoxOffice: String,
    @NonNull
    @ColumnInfo(name = "Production")
    var Production: String,
    @NonNull
    @ColumnInfo(name = "Website")
    var Website: String,
    @NonNull
    @ColumnInfo(name = "Response")
    var Response: String
) : Serializable