package com.example.mymovieslist.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymovieslist.R.drawable
import com.example.mymovieslist.core.components.RetryScreen
import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.presentation.viewmodel.MainViewModel
import com.example.theme.MyMoviesTheme
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val state by viewModel.screenState.collectAsState()

    when {
        state.isLoading -> ShowLoading()
        state.moviesList.isEmpty() -> RetryScreen(viewModel::onRetryButtonClicked)
        else -> MakeList(state.moviesList)
    }
}

@Composable
private fun MakeList(moviesList: List<Movie>) {
    Column {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
            style = MaterialTheme.typography.h4,
            color = Color.White,
            text = "Popular",
        )
        LazyVerticalGrid(
            columns = Adaptive(128.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(moviesList) { movie ->
                    MovieView(movie)
                }
            }
        )
    }
}

@Composable
private fun MovieView(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        InflateImage(posterUrl = movie.posterUrl)
    }

}

@Composable
private fun InflateImage(posterUrl: String) =
    CoilImage(
        modifier = Modifier.size(height = 200.dp, width = 140.dp),
        imageModel = posterUrl,
        contentScale = ContentScale.Crop,
        error = ImageBitmap.imageResource(id = drawable.icon_film),
    )

@Composable
private fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun MovieListPreview() {
    MyMoviesTheme {
        MakeList(
            (0..10).map {
                Movie(
                    title = "Puss in Boots: The Last Wish",
                    originalLanguage = "en",
                    releaseDate = "2022-12-07",
                    posterUrl = "https://image.tmdb.org/t/p/original/kuf6dutpsT0vSVehic3EZIqkOBt.jpg"
                )
            }
        )
    }
}