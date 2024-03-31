package com.example.mymovieslist.usecase

import app.cash.turbine.test
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetPopularMoviesListUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class GetPopularMoviesListUseCaseTest {

    private val repository: MovieRepository = mockk(relaxed = true)
    private val getPopularMoviesListUseCase = GetPopularMoviesListUseCase(repository)

    @Test
    fun `invoke should return list with movies`() = runTest {
        // Given
        val expected = popularMoviesList
        every { repository.getPopularMoviesList(1) } returns flowOf(popularMoviesList)

        // When
        val result = getPopularMoviesListUseCase.invoke(1)

        // Then
        result.test {
            assertEquals(awaitItem(), expected)
            awaitComplete()
        }
    }
}

private val popularMoviesList = listOf(
    Movie(
        title = "UiMXAVh8",
        releaseDate = "r94",
        originalLanguage = "14SXz5pI",
        posterUrl = "https://image.tmdb.org/t/p/original/bY3jI0",
    )
)