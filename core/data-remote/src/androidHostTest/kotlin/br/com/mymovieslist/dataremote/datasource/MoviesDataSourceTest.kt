package br.com.mymovieslist.dataremote.datasource

import app.cash.turbine.test
import br.com.extensions.RequestExceptions
import br.com.mymovieslist.dataremote.network.api.MovieService
import br.com.mymovieslist.dataremote.network.datasource.MoviesDataSource
import br.com.mymovieslist.dataremote.network.datasource.MoviesDataSourceImpl
import br.com.mymovieslist.dataremote.network.datasource.model.FetchPopularMoviesResponse
import br.com.mymovieslist.dataremote.network.datasource.model.MovieResponse
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.io.IOException
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
internal class MoviesDataSourceTest {

    private val service: MovieService = mockk(relaxed = true)
    private lateinit var moviesDataSource: MoviesDataSource

    @BeforeTest
    fun setUp() {
        moviesDataSource = MoviesDataSourceImpl(service)
    }

    private val ioException = IOException("No connection")
    private val httpResponse: HttpResponse = mockk(relaxed = true) {
        every { status } returns HttpStatusCode.NotFound
    }
    private val responseException = ResponseException(httpResponse, "Not Found")

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
    fun `fetchPopularMovie should throw NoConnectionException when service receives IOException`() =
        runTest {
            // Given
            val exception = ioException
            coEvery {
                service.fetchPopularMovies(1)
            } throws exception

            // When
            val result = moviesDataSource.fetchPopularMovies(1)

            // Then
            result.test {
                val error = awaitError()
                assert(error is RequestExceptions.NoConnectionException)
            }
        }

    @Test
    fun `fetchPopularMovie should throw HttpError when service receives ResponseException`() =
        runTest {
            // Given
            val exception = responseException
            val expected = RequestExceptions.HttpError(
                errorMessage = exception.message.orEmpty(),
                code = exception.response.status.value
            )
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
