package com.example.mymovieslist.stubs

import com.example.mymovieslist.data_remote.network.datasource.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data_remote.network.datasource.model.MovieResponse
import com.example.mymovieslist.domain.model.Movie
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
            posterPath = "/bY3jI0",
        )
    )
)

val popularMoviesList = listOf(
    Movie(
        title = "UiMXAVh8",
        releaseDate = "r94",
        originalLanguage = "14SXz5pI",
        posterUrl = "https://image.tmdb.org/t/p/original/bY3jI0",
    )
)