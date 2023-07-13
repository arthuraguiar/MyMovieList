package com.example.mymovieslist.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.mymovieslist.R
import com.example.mymovieslist.core.components.RetryScreen
import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.domain.model.MovieSection
import com.example.theme.MyMoviesTheme
import com.example.theme.md_theme_dark_onSurface

@Composable
fun HomeScreen(
    loading: Boolean,
    movieList: List<Movie>,
    retry: () -> Unit
) {

    when {
        loading -> ShowLoading()
        movieList.isEmpty() -> RetryScreen(retry)
        else -> MakeList(makeMovieSection(movieList))
    }
}

@Composable
private fun MakeList(movieSectionList: List<MovieSection>) {
    LazyColumn {
        items(movieSectionList) { section ->
            HomeSection(title = section.title) {
                MovieHorizontalGrid(section.movies)
            }
        }
    }
}

@Composable
private fun MovieHorizontalGrid(movies: List<Movie>) {
    LazyHorizontalGrid(
        rows = Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(400.dp)
    ) {

        items(movies) { movie ->
            MovieView(movie)
        }
    }
}

@Composable
fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            color = md_theme_dark_onSurface,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
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
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(posterUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_film),
                    contentDescription = null
                )
                CircularProgressIndicator()
            }
        }
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
        val movieList = (0..10).map {
            Movie(
                id = it,
                title = "Puss in Boots: The Last Wish",
                originalLanguage = "en",
                releaseDate = "2022-12-07",
                posterUrl = "https://image.tmdb.org/t/p/original/kuf6dutpsT0vSVehic3EZIqkOBt.jpg"
            )
        }
        MakeList(
            listOf(
                MovieSection(
                    "Popular",
                    movieList
                ),
                MovieSection(
                    "Action",
                    movieList
                )
            )
        )
    }
}

//TODO map other api endpoints
private fun makeMovieSection(movies: List<Movie>) =
    mutableListOf(
        MovieSection(
            "Popular",
            movies
        ),
        MovieSection(
            "Action",
            movies.reversed()
        ),
        MovieSection(
            "Animation",
            movies.sortedBy { it.title }
        )
    )
