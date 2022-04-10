package com.example.mymovieslist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.mymovieslist.BuildConfig
import com.example.mymovieslist.core.extensions.observe
import com.example.mymovieslist.databinding.ActivityMainBinding
import com.example.mymovieslist.presentation.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setOpObserver()
    }

    private fun setUpRecyclerView() {
        binding.moviesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    private fun setOpObserver() {
        viewModel.screenState.observe(this) { state ->
            moviesAdapter.submitList(state.moviesList)
            binding.progressBar.isVisible = state.isEmptyState
            binding.progressBar.isVisible = state.isLoading
        }
    }
}