package com.example.mymovieslist.presentation

import androidx.lifecycle.viewModelScope
import com.example.mymovieslist.core.viewmodel.BaseViewModel
import com.example.mymovieslist.domain.usecase.GetPopularMoviesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase
) : BaseViewModel<MainState>(MainState()) {
    init {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        getPopularMoviesListUseCase()
            .flowOn(Dispatchers.IO)
            .onStart { setState { it.copy(isLoading = true) } }
            .onCompletion { setState { it.copy(isLoading = false) } }
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