package com.example.mymovieslist.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.model.Movie
import com.example.domain.usecase.GetPopularMoviesListUseCase
import com.example.mymovieslist.core.di.IoDispatcher
import com.example.mymovieslist.core.viewmodel.BaseViewModel
import com.example.mymovieslist.presentation.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

private const val PAGE_SIZE = 20

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<MainState>(MainState()) {

    private var page: Int = 1

    init {
        getPopularMovies()
    }

    fun getPopularMovies() = viewModelScope.launch {
        getPopularMoviesListUseCase(page)
            .flowOn(dispatcher)
            .onStart { setState { copy(isLoading = true) } }
            .onCompletion { setState { copy(isLoading = false) } }
            .catch { handleError() }
            .collect { movies ->
                handleSuccess(movies)
            }
    }

    private fun handleSuccess(newList: List<Movie>) {
        setState {
            copy(
                isLoading = false,
                popularMovies = popularMovies.copy(
                    movies = popularMovies.movies + newList
                ),
                nowPlayingMovies = nowPlayingMovies.copy(
                    movies = nowPlayingMovies.movies + newList
                ),
                upcomingMovies = upcomingMovies.copy(
                    movies = upcomingMovies.movies + newList
                ),
            )
        }
        page++
    }

    fun onRetry() {
        getPopularMovies()
    }

    private fun handleError() {
        setState {
            getErrorState()
        }
    }
}
