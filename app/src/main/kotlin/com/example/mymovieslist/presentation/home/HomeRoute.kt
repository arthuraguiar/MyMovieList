package com.example.mymovieslist.presentation.home

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.mymovieslist.presentation.viewmodel.MainViewModel

@Composable
fun HomeRoute(viewModel: MainViewModel) {

    val state by viewModel.screenState.collectAsState()

    val listState = rememberLazyGridState()

    val shouldStartPaginate by remember {
        derivedStateOf {
            (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (listState.layoutInfo.totalItemsCount - 3)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate) {
        if (shouldStartPaginate) viewModel.getPopularMovies()
    }

    HomeScreen(
        loading = state.isLoading,
        movieList = state.moviesList,
        listState = listState,
        retry = viewModel::onRetry,
    )
}
