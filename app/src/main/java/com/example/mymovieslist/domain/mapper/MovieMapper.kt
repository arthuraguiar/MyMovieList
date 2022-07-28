package com.example.mymovieslist.domain.mapper

import com.example.mymovieslist.R
import com.example.mymovieslist.core.resource.ResourceProvider
import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import com.example.mymovieslist.data.model.MovieResponse
import com.example.mymovieslist.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor(resourceProvider: ResourceProvider) {

    private val basePosterUrl = resourceProvider.getStringResource(R.string.poster_base_url)

    fun toMovieList(moviesResponse: FetchPopularMoviesResponse): List<Movie> {
        return moviesResponse.results.map { it.toMovieEntity() }
    }

    private fun MovieResponse.toMovieEntity(): Movie{
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
