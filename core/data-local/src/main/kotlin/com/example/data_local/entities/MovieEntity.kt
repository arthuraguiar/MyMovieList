package com.example.data_local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("original_language")
    val originalLanguage: String,

    @ColumnInfo("release_date")
    val releaseDate: String,

    @ColumnInfo("poster_url")
    val posterUrl: String,

    @ColumnInfo("page")
    val page: Int
)