package br.com.mymovieslist.presentation

import app.cash.turbine.test
import br.com.CoroutinesTestRule
import br.com.domain.model.Movie
import br.com.domain.usecase.GetPopularMoviesListUseCase
import br.com.mymovieslist.presentation.viewmodel.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import kotlin.test.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
internal class HomeViewModelTest {

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
    private lateinit var viewModel: HomeViewModel


    @Test
    fun `init should show error layout when error occurred`() = runTest {
        // Given
        val initState = HomeState()
        val errorState = HomeState(isErrorState = true)
        coEvery { useCase.invoke(1) } returns flow { throw Throwable() }

        // When
        viewModel = HomeViewModel(useCase, coroutinesTestRule.standardTestDispatcher)

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
        val initState = HomeState()
        val successState = HomeState(moviesList = popularMoviesList, isLoading = true)
        coEvery { useCase.invoke(1) } returns flowOf(popularMoviesList)

        // When
        viewModel = HomeViewModel(useCase, coroutinesTestRule.standardTestDispatcher)

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
        val initState = HomeState()
        val successState =
            HomeState(moviesList = emptyList(), isLoading = true, isEmptyState = true)
        coEvery { useCase.invoke(1) } returns flowOf(emptyList())

        // When
        viewModel = HomeViewModel(useCase, coroutinesTestRule.standardTestDispatcher)

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