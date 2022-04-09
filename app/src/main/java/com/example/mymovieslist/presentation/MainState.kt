package com.example.mymovieslist.presentation

import com.example.mymovieslist.core.viewmodel.state.ScreenState
import com.example.mymovieslist.domain.model.Movie

data class MainState(
    val isLoading: Boolean = false,
    val moviesList: List<Movie> = emptyList(),
    val isEmptyState: Boolean = moviesList.isEmpty()
) : ScreenState
