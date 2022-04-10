package com.example.mymovieslist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchPopularMoviesResponse(
    @SerialName("results")
    val results: List<MovieResponse>
)
