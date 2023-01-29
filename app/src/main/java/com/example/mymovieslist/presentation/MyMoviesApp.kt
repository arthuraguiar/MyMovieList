package com.example.mymovieslist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymovieslist.R
import com.example.mymovieslist.domain.model.Movie
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MyMoviesApp(uiState: MainState, tryAgain: () -> Unit) {
    StatusBarIcons()

    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            HandleState(state = uiState, tryAgain)
        }
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
private fun HandleState(state: MainState, tryAgain: () -> Unit) {
    when {
        state.isLoading -> ShowLoading()
        state.moviesList.isEmpty() -> TryAgain(tryAgain)
        state.moviesList.isNotEmpty() -> MakeList(state.moviesList)
    }
}

@Composable
fun TryAgain(tryAgain: () -> Unit) {
    TextButton(
        onClick = tryAgain,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            stringResource(id = R.string.retry),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MakeList(moviesList: List<Movie>) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        items(items = moviesList) { movie ->
            MovieView(movie)
        }
    }
}

@Composable
private fun MovieView(movie: Movie) {
    Row(
        Modifier
            .height(50.dp)
            .padding(top = 4.dp, bottom = 4.dp)
    ) {
        InflateImage(posterUrl = movie.posterUrl)
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = movie.title)
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = movie.releaseDate
            )
        }
    }
}

@Composable
private fun InflateImage(posterUrl: String) =
    CoilImage(
        modifier = Modifier
            .width(120.dp)
            .height(80.dp),
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

@Preview
@Composable
fun MainPreview() {
    MaterialTheme {
        HandleState(state = MainState(), {})
    }
}