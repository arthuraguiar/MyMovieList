package com.example.mymovieslist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymovieslist.R
import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.presentation.adapter.MoviesAdapter
import com.google.android.material.composethemeadapter.MdcTheme
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SetOpObserver()
            }
        }
    }

    @Composable
    private fun SetOpObserver() {
        val uiState by viewModel.screenState.collectAsState()
        HandleState(state = uiState)

        /*{ state ->
            moviesAdapter.submitList(state.moviesList)
            binding.progressBar.isVisible = state.isEmptyState
            binding.progressBar.isVisible = state.isLoading
            binding.errorLayout.root.isVisible = state.isErrorState
            binding.emptyStateLayout.root.isVisible = state.isEmptyState
        }*/
    }

    @Composable
    private fun HandleState(state: MainState) {
        when {
            state.isLoading -> ShowLoading()
            state.moviesList.isNotEmpty() -> MakeList(state.moviesList)
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
                Row(Modifier.height(50.dp)) {
                    InflateImage(posterUrl = movie.posterUrl)
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(text = movie.title)
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = movie.releaseDate
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun InflateImage(posterUrl: String) =
        GlideImage(
            modifier = Modifier
                .height(40.dp)
                .width(60.dp),
            imageModel = posterUrl,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(id = R.drawable.icon_film),
        )


    @Composable
    private fun ShowLoading() {

        Surface(modifier = Modifier.fillMaxWidth()) {
            CircularProgressIndicator()
        }
    }

    @Preview
    @Composable
    fun MainPreview() {
        MaterialTheme {
            HandleState(state = MainState())
        }
    }
}