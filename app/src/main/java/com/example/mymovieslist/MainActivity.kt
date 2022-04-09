package com.example.mymovieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mymovieslist.core.extensions.observe
import com.example.mymovieslist.databinding.ActivityMainBinding
import com.example.mymovieslist.presentation.MainViewModel
import com.example.mymovieslist.presentation.adapter.MoviesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
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
        viewModel.screenState.observe(this) {
            moviesAdapter.submitList(it.moviesList)
        }
    }
}