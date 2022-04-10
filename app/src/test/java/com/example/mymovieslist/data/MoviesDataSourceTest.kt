package com.example.mymovieslist.data

import app.cash.turbine.test
import com.example.mymovieslist.data.datasource.MoviesDataSource
import com.example.mymovieslist.data.datasource.MoviesDataSourceImpl
import com.example.mymovieslist.data.network.MovieService
import com.example.mymovieslist.domain.model.RequestExceptions
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class MoviesDataSourceTest {

    private val service: MovieService = mockk(relaxed = true)
    private lateinit var moviesDataSource: MoviesDataSource

    @Before
    fun setUp() {
        moviesDataSource = MoviesDataSourceImpl(service)
    }


    @Test
    fun `fetchPopularMovie should throw NoConnectionException when service receives SocketTimeoutException`() =
        runTest {
            // Given
            val exception = socketTimeoutException
            val expected = RequestExceptions.NoConnectionException
            coEvery {
                service.fetchPopularMovies()
            } throws exception

            // When
            val result = moviesDataSource.fetchPopularMovies()

            // Then
            result.test {
                val error = expectError()
                assertEquals(error, expected)
            }
        }

    @Test
    fun `fetchPopularMovies should return FetchPopularMoviesResponse When Success`() =
        runTest {
            // Given
            val expected = fetchPopularMoviesResponse
            coEvery {
                service.fetchPopularMovies()
            } returns expected

            // When
            val result = moviesDataSource.fetchPopularMovies()

            // Then
            result.test {
                assertEquals(expectItem(), expected)
                expectComplete()
            }
        }

}



