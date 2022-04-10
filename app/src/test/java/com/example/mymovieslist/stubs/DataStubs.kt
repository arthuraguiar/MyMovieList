package com.example.mymovieslist.data

import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data.model.MovieResponse
import java.net.SocketTimeoutException

val socketTimeoutException = SocketTimeoutException()

val fetchPopularMoviesResponse = FetchPopularMoviesResponse(
    results = listOf(
        MovieResponse(
            title = "UiMXAVh8",
            releaseDate = "r94",
            originalLanguage = "14SXz5pI",
            posterPath = "bY3jI0",
        )
    )
)