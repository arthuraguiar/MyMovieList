package com.example.mymovieslist.data.mapper

import com.example.mymovieslist.data_remote.network.datasource.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data_remote.network.datasource.model.MovieResponse
import com.example.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    private val basePosterUrl = "https://image.tmdb.org/t/p/original"

    fun toMovieList(moviesResponse: FetchPopularMoviesResponse): List<Movie> {
        return moviesResponse.results.map { it.toMovieEntity() }
    }

    private fun MovieResponse.toMovieEntity(): Movie {
        return Movie(
            title = this.title,
            releaseDate = this.releaseDate,
            originalLanguage = this.originalLanguage,
            posterUrl = buildPosterImageUrl(this.posterPath).orEmpty()
        )
    }

    private fun buildPosterImageUrl(posterPath:String?): String?{
        return if (posterPath != null) {
            basePosterUrl.plus(posterPath)
        } else
            null
    }
}
