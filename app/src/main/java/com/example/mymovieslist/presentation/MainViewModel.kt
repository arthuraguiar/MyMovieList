package com.example.mymovieslist.presentation

import androidx.lifecycle.viewModelScope
import com.example.mymovieslist.core.viewmodel.BaseViewModel
import com.example.mymovieslist.domain.usecase.GetPopularMoviesListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MainState>(MainState()) {
    init {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        getPopularMoviesListUseCase()
            .flowOn(dispatcher)
            .onStart { setState { it.getLoadingState(isLoading = true) } }
            .onCompletion { setState { it.getLoadingState(isLoading = false) } }
            .catch { handleError(it) }
            .collect { movies ->
                setState { it.getSuccessState(movies) }
            }
    }

    fun onRetryButtonClicked() {
        getPopularMovies()
    }

    private fun handleError(throwable: Throwable) {
        setState { it.getErrorState() }
    }
}