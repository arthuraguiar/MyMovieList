package com.example.mymovieslist.domain.usecase

import com.example.mymovieslist.data.repository.MovieRepositoryImpl
import com.example.mymovieslist.domain.model.Movie
import com.example.mymovieslist.domain.repository.MovieRepository

class GetPopularMoviesListUseCase(
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) {

    operator fun invoke(): List<Movie> {
        return movieRepository.getPopularMoviesList()
    }

}