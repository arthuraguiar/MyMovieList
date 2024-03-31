package com.example.mymovieslist.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.mymovieslist.core.di.IoDispatcher
import com.example.mymovieslist.core.viewmodel.BaseViewModel
import com.example.domain.usecase.GetPopularMoviesListUseCase
import com.example.mymovieslist.presentation.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<MainState>(MainState()) {

    private var page: Int = 1
    init {
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        getPopularMoviesListUseCase(page)
            .flowOn(dispatcher)
            .onStart { setState { it.getLoadingState(isLoading = true) } }
            .onCompletion { setState { it.getLoadingState(isLoading = false) } }
            .catch { handleError() }
            .collect { movies ->
                setState { it.getSuccessState(movies) }
            }
    }

    fun onRetry() {
        getPopularMovies()
    }

    private fun handleError() {
        setState { it.getErrorState() }
    }
}
