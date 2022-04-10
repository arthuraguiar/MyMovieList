package com.example.mymovieslist.domain.usecase

import app.cash.turbine.test
import com.example.mymovieslist.domain.repository.MovieRepository
import com.example.mymovieslist.stubs.popularMoviesList
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
        every { repository.getPopularMoviesList() } returns flowOf(popularMoviesList)

        // When
        val result = getPopularMoviesListUseCase.invoke()

        // Then
        result.test {
            assertEquals(expectItem(), expected)
            expectComplete()
        }
    }

}