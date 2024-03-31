package com.example.mymovieslist.data.repository

import app.cash.turbine.test
import com.example.domain.model.Movie
import com.example.mymovieslist.data_remote.network.datasource.MoviesDataSource
import com.example.mymovieslist.data.mapper.MovieMapper
import com.example.domain.repository.MovieRepository
import com.example.mymovieslist.data_remote.network.datasource.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data_remote.network.datasource.model.MovieResponse
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class MovieRepositoryTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var movieMapper: MovieMapper
    private val moviesDataSource: MoviesDataSource = mockk(relaxed = true)

    private val fetchPopularMoviesResponse = FetchPopularMoviesResponse(
        results = listOf(
            MovieResponse(
                title = "UiMXAVh8",
                releaseDate = "r94",
                originalLanguage = "14SXz5pI",
                posterPath = "/bY3jI0",
            )
        )
    )

    private val popularMoviesList = listOf(
        Movie(
            title = "UiMXAVh8",
            releaseDate = "r94",
            originalLanguage = "14SXz5pI",
            posterUrl = "https://image.tmdb.org/t/p/original/bY3jI0",
        )
    )

    @Before
    fun setUp() {

        movieMapper = MovieMapper()
        movieRepository = MovieRepositoryImpl(
            moviesDataSource = moviesDataSource,
            movieMapper = movieMapper
        )
    }

    @Test
    fun `getPopularMoviesList should return mapped structure`() = runTest {
        // Given
        val expected = popularMoviesList
        coEvery {
            moviesDataSource.fetchPopularMovies(1)
        } returns flowOf(fetchPopularMoviesResponse)

        // When
        val result = movieRepository.getPopularMoviesList(1)

        // Then
        result.test {
            assertEquals(awaitItem(), expected)
            awaitComplete()
        }
    }
}