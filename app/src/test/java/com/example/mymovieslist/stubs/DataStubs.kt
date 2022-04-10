package com.example.mymovieslist.stubs

import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data.model.MovieResponse
import io.mockk.mockk
import retrofit2.HttpException
import java.net.SocketTimeoutException

val socketTimeoutException = SocketTimeoutException()
val httpException: HttpException = mockk(relaxed = true)

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