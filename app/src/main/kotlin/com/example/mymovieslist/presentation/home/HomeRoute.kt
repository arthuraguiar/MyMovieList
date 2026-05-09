package com.example.mymovieslist.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mymovieslist.presentation.viewmodel.HomeViewModel

@Composable
internal fun HomeRoute(viewModel: HomeViewModel) {

    val state by viewModel.screenState.collectAsState()

    HomeScreen(
        loading = state.isLoading,
        movieList = state.moviesList,
        retry = viewModel::onRetry,
    )
}
