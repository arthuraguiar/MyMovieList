package br.com.mymovieslist.presentation

import br.com.mymovieslist.core.viewmodel.state.ScreenState
import br.com.domain.model.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val moviesList: List<Movie> = emptyList(),
    val isEmptyState: Boolean = false,
    val isErrorState: Boolean = false
) : ScreenState {
    fun getSuccessState(moviesList: List<Movie>): HomeState {
        return this.copy(
            moviesList = moviesList,
            isErrorState = false,
            isEmptyState = moviesList.isEmpty(),
        )
    }

    fun getErrorState(): HomeState {
        return this.copy(
            isErrorState = true
        )
    }

    fun getLoadingState(isLoading: Boolean): HomeState{
        return this.copy(
            isLoading = isLoading,
            isErrorState = false,
        )
    }
}
