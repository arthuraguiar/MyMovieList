package br.com.mymovieslist.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.domain.usecase.GetPopularMoviesListUseCase
import br.com.mymovieslist.core.viewmodel.BaseViewModel
import br.com.mymovieslist.presentation.HomeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel<HomeState>(HomeState()) {

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
