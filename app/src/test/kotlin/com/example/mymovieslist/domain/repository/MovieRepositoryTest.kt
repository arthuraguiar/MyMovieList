package com.example.mymovieslist.domain.repository

import app.cash.turbine.test
import com.example.mymovieslist.R
import com.example.mymovieslist.core.resource.ResourceProvider
import com.example.mymovieslist.data_remote.network.datasource.MoviesDataSource
import com.example.mymovieslist.data.repository.MovieRepositoryImpl
import com.example.mymovieslist.domain.mapper.MovieMapper
import com.example.mymovieslist.stubs.fetchPopularMoviesResponse
import com.example.mymovieslist.stubs.popularMoviesList
import io.mockk.coEvery
import io.mockk.every
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
    private val resourceProvider = ResourceProvider(mockk(relaxed = true))
    private lateinit var movieMapper: MovieMapper
    private val moviesDataSource: MoviesDataSource = mockk(relaxed = true)

    @Before
    fun setUp() {
        every {
            resourceProvider.getStringResource(R.string.poster_base_url)
        } returns "https://image.tmdb.org/t/p/original"

        movieMapper = MovieMapper(resourceProvider)
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
            moviesDataSource.fetchPopularMovies()
        } returns flowOf(fetchPopularMoviesResponse)

        // When
        val result = movieRepository.getPopularMoviesList()

        // Then
        result.test {
            assertEquals(awaitItem(), expected)
            awaitComplete()
        }
    }
}