package com.example.mymovieslist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.isVisible
import com.example.mymovieslist.core.extensions.observe
import com.example.mymovieslist.databinding.ActivityMainBinding
import com.example.mymovieslist.presentation.adapter.MoviesAdapter
import com.google.android.material.composethemeadapter.MdcTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MdcTheme {
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
        }
    }

    @Composable
    private fun ShowLoading() {

        Surface(modifier = Modifier.fillMaxWidth()) {
            CircularProgressIndicator()
        }
    }

    @Preview
    @Composable
    fun MainPreview() {
        MdcTheme {
            HandleState(state = MainState())
        }
    }
}