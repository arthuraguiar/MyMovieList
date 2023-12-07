package com.example.mymovieslist.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mymovieslist.presentation.viewmodel.MainViewModel

@Composable
fun HomeRoute(viewModel: MainViewModel) {

    val state by viewModel.screenState.collectAsState()

    HomeScreen(
        loading = state.isLoading,
        movieList = state.moviesList,
        retry = viewModel::onRetry,
    )
}
