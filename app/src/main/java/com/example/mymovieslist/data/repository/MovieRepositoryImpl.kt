package com.example.mymovieslist.data.repository

import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {
    override fun getPopularMoviesList(): List<Movie> {
        return listOf(
            Movie(
                title = "Sonic",
                originalLanguage = "eng",
                releaseDate = "2022-03-30",
                posterUrl = """https:/image.tmdb.org/t/p/original/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg""",
            )
        )
    }
}