package br.com.mymovieslist.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.mymovieslist.presentation.viewmodel.HomeViewModel

@Composable
internal fun HomeRoute(viewModel: HomeViewModel) {

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    HomeScreen(
        loading = state.isLoading,
        movieList = state.moviesList,
        retry = viewModel::onRetry,
    )
}
