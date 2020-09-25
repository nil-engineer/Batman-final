package com.test.batman.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "searchItemModel")

data class SearchItemModel(
    @NonNull
    @ColumnInfo(name = "Title")
    var Title: String,
    @NonNull
    @ColumnInfo(name = "Year")
    var Year: String,
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "imdbID")
    var imdbID: String,
    @NonNull
    @ColumnInfo(name = "Type")
    var Type: String,
    @NonNull
    @ColumnInfo(name = "Poster")
    var Poster: String
) : Serializable