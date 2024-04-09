package com.example.mymovieslist.presentation

import com.example.mymovieslist.core.viewmodel.state.ScreenState
import com.example.domain.model.Movie

data class MainState(
    val isLoading: Boolean = false,
    val moviesList: List<Movie> = emptyList(),
    val isEmptyState: Boolean = false,
    val isErrorState: Boolean = false,
) : ScreenState {

    fun getErrorState(): MainState {
        return this.copy(
            isErrorState = true
        )
    }
}
