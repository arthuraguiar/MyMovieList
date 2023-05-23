package com.example.mymovieslist.data.datasource

import app.cash.turbine.test
import com.example.domain.RequestExceptions
import com.example.mymovieslist.data.network.MovieService
import com.example.mymovieslist.stubs.fetchPopularMoviesResponse
import com.example.mymovieslist.stubs.httpException
import com.example.mymovieslist.stubs.socketTimeoutException
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
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
                val error = awaitError()
                assertEquals(error, expected)
            }
        }

    @Test
    fun `fetchPopularMovie should throw HttpError when service receives HttpException`() =
        runTest {
            // Given
            val exception = httpException
            val expected = RequestExceptions.HttpError(exception.message(), exception.code())
            coEvery {
                service.fetchPopularMovies()
            } throws exception

            // When
            val result = moviesDataSource.fetchPopularMovies()

            // Then
            result.test {
                val error = awaitError()
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
                assertEquals(awaitItem(), expected)
                awaitComplete()
            }
        }
}



