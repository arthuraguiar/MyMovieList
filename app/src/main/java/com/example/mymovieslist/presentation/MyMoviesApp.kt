package com.example.mymovieslist.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymovieslist.R
import com.example.mymovieslist.core.components.RetryScreen
import com.example.mymovieslist.domain.model.Movie
import com.example.theme.MyMoviesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MyMoviesApp(uiState: MainState, tryAgain: () -> Unit) {
    StatusBarIcons()

    Scaffold(){ paddingValues ->
        HandleState(state = uiState, modifier = Modifier.padding(paddingValues), tryAgain)
    }
}

@Composable
private fun StatusBarIcons() {
    val systemUiController = rememberSystemUiController()
    val darkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
    }
}

@Composable
private fun HandleState(state: MainState, modifier: Modifier, tryAgain: () -> Unit) {
    when {
        state.isLoading -> ShowLoading()
        state.moviesList.isEmpty() -> RetryScreen(tryAgain)
        else -> MakeList(state.moviesList, modifier)
    }
}

@Composable
private fun MakeList(moviesList: List<Movie>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(items = moviesList) { movie ->
            MovieView(movie)
        }
    }
}

@Composable
private fun MovieView(movie: Movie, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.width(192.dp)
        ) {
            InflateImage(posterUrl = movie.posterUrl)
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.body1)
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = movie.releaseDate
                )
            }
        }
    }
}

@Composable
private fun InflateImage(posterUrl: String) =
    CoilImage(
        modifier = Modifier.size(86.dp),
        imageModel = posterUrl,
        contentScale = ContentScale.Crop,
        error = ImageBitmap.imageResource(id = R.drawable.icon_film),
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

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainPreview() {
    MyMoviesTheme {
        HandleState(state = MainState(), Modifier) {}
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
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