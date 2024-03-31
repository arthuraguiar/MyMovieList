package com.example.mymovieslist.data_remote.network.datasource.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("title")
    val title: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("poster_path")
    val posterPath: String?,
)