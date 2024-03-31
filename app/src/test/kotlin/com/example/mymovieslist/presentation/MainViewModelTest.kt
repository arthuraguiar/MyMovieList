package com.example.mymovieslist.presentation

import app.cash.turbine.test
import com.example.CoroutinesTestRule
import com.example.domain.model.Movie
import com.example.domain.usecase.GetPopularMoviesListUseCase
import com.example.mymovieslist.presentation.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class MainViewModelTest {

    private val popularMoviesList = listOf(
        Movie(
            title = "UiMXAVh8",
            releaseDate = "r94",
            originalLanguage = "14SXz5pI",
            posterUrl = "https://image.tmdb.org/t/p/original/bY3jI0",
        )
    )

    @get: Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val useCase: GetPopularMoviesListUseCase = mockk(relaxed = true)
    private lateinit var viewModel: MainViewModel


    @Test
    fun `init should show error layout when error occurred`() = runTest {
        // Given
        val initState = MainState()
        val errorState = MainState(isErrorState = true)
        coEvery { useCase.invoke(1) } returns flow { throw Throwable() }

        // When
        viewModel = MainViewModel(useCase, coroutinesTestRule.standardTestDispatcher)

        // Then
        viewModel.screenState.test {
            assertEquals(awaitItem(), initState)
            assertEquals(awaitItem(), initState.copy(isLoading = true))
            assertEquals(awaitItem(), initState.copy(isLoading = false))
            assertEquals(awaitItem(), errorState)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `init should get popular movies list with data`() = runTest {
        // Given
        val initState = MainState()
        val successState = MainState(moviesList = popularMoviesList, isLoading = true)
        coEvery { useCase.invoke(1) } returns flowOf(popularMoviesList)

        // When
        viewModel = MainViewModel(useCase, coroutinesTestRule.standardTestDispatcher)

        // Then
        viewModel.screenState.test {
            assertEquals(awaitItem(), initState)
            assertEquals(awaitItem(), initState.copy(isLoading = true))
            assertEquals(awaitItem(), successState)
            assertEquals(awaitItem(), successState.copy(isLoading = false))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `init should get empty popular movies list`() = runTest {
        // Given
        val initState = MainState()
        val successState =
            MainState(moviesList = emptyList(), isLoading = true, isEmptyState = true)
        coEvery { useCase.invoke(1) } returns flowOf(emptyList())

        // When
        viewModel = MainViewModel(useCase, coroutinesTestRule.standardTestDispatcher)

        // Then
        viewModel.screenState.test {
            assertEquals(awaitItem(), initState)
            assertEquals(awaitItem(), initState.copy(isLoading = true))
            assertEquals(awaitItem(), successState)
            assertEquals(awaitItem(), successState.copy(isLoading = false))
            cancelAndIgnoreRemainingEvents()
        }
    }
}