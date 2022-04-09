package com.example.mymovieslist.data.repository

import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {
    override fun getPopularMoviesList(): List<Movie> {
        return listOf(
            Movie(
                title = "TuQ0iU5X",
                originalLanguage = "RQg8VV19",
                overview = "Ac3GB3rJ",
                posterUrl = "7w4FgXU",
            )
        )
    }
}