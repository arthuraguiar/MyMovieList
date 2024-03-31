package com.example.mymovieslist.data_remote.datasource

import app.cash.turbine.test
import com.example.common.extensions.RequestExceptions
import com.example.mymovieslist.data_remote.network.api.MovieService
import com.example.mymovieslist.data_remote.network.datasource.MoviesDataSource
import com.example.mymovieslist.data_remote.network.datasource.MoviesDataSourceImpl
import com.example.mymovieslist.data_remote.network.datasource.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data_remote.network.datasource.model.MovieResponse
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest
import retrofit2.HttpException
import java.net.SocketTimeoutException
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

    private val socketTimeoutException = SocketTimeoutException()
    private val httpException: HttpException = mockk(relaxed = true)

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


    @Test
    fun `fetchPopularMovie should throw NoConnectionException when service receives SocketTimeoutException`() =
        runTest {
            // Given
            val exception = socketTimeoutException
            val expected = RequestExceptions.NoConnectionException
            coEvery {
                service.fetchPopularMovies(1)
            } throws exception

            // When
            val result = moviesDataSource.fetchPopularMovies(1)

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
                service.fetchPopularMovies(1)
            } throws exception

            // When
            val result = moviesDataSource.fetchPopularMovies(1)

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
                service.fetchPopularMovies(1)
            } returns expected

            // When
            val result = moviesDataSource.fetchPopularMovies(1)

            // Then
            result.test {
                assertEquals(awaitItem(), expected)
                awaitComplete()
            }
        }
}



