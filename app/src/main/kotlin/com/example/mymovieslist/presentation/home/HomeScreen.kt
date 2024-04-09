package com.example.mymovieslist.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.collectIsDraggedAsState
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
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.domain.model.Movie
import com.example.domain.model.MovieSection
import com.example.mymovieslist.R
import com.example.theme.md_theme_dark_onSurface
import kotlin.math.absoluteValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    loading: Boolean,
    nowPlayingMovies: MovieSection,
    popularMovies: MovieSection,
    upcomingMovies: MovieSection,
    listState: LazyGridState = rememberLazyGridState(),
    retry: () -> Unit
) {
    Scaffold { paddingValues ->
        ScreenLoading(show = loading)
        LazyColumn(Modifier.padding(paddingValues)) {
            item {

                HomeSection(title = nowPlayingMovies.title) {
                    val pagerState = rememberPagerState {
                        nowPlayingMovies.movies.size
                    }
                    AwesomeCarousel(
                        pagerState,
                        nowPlayingMovies.movies
                    )
                }
            }
            item {
                HomeSection(title = popularMovies.title) {
                    MovieHorizontalGrid(popularMovies.movies)
                }
            }
            item {
                HomeSection(title = upcomingMovies.title) {
                    MovieHorizontalGrid(upcomingMovies.movies)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AwesomeCarousel(
    pagerState: PagerState,
    itemList: List<Movie> = emptyList(),
    autoScrollDuration: Long = 3000L
) {
    /*    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
        if (isDragged.not()) {
            with(pagerState) {
                var currentPageKey by remember { mutableStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }*/

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 32.dp),
        pageSpacing = 16.dp
    ) { page ->
        MovieView(Modifier.carouselTransition(page, pagerState), itemList[page])
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = 0.7f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }

@Composable
private fun MovieHorizontalGrid(movies: List<Movie>, rowsCount: Int = 2) {
    LazyHorizontalGrid(
        rows = Fixed(rowsCount),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(400.dp)
    ) {

        items(movies) { movie ->
            MovieView(movie = movie)
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
            style = MaterialTheme.typography.titleLarge,
            color = md_theme_dark_onSurface,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
private fun MakeList(modifier: Modifier, listState: LazyGridState, movieList: List<Movie>) {
    Column(modifier) {
        Text(
            text = "Popular",
            style = MaterialTheme.typography.titleLarge,
            color = md_theme_dark_onSurface,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        LazyVerticalGrid(
            state = listState,
            columns = Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(movieList) { movie ->
                MovieView(movie = movie)
            }
        }
    }
}

@Composable
private fun MovieView(modifier: Modifier = Modifier, movie: Movie) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
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
            .memoryCacheKey(posterUrl)
            .diskCacheKey(posterUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
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
private fun ScreenLoading(show: Boolean = false) {
    if (!show) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}
