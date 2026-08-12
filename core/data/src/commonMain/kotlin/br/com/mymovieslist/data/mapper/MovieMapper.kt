package br.com.mymovieslist.data.mapper

import br.com.domain.model.Movie
import br.com.mymovieslist.dataremote.network.datasource.model.FetchPopularMoviesResponse
import br.com.mymovieslist.dataremote.network.datasource.model.MovieResponse

internal class MovieMapper {

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

    private fun buildPosterImageUrl(posterPath: String?): String? {
        return if (posterPath != null) {
            basePosterUrl.plus(posterPath)
        } else {
            null
        }
    }
}
