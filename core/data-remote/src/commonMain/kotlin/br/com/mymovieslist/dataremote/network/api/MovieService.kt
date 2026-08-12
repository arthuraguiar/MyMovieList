package br.com.mymovieslist.dataremote.network.api

import br.com.mymovieslist.dataremote.network.datasource.model.FetchPopularMoviesResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

private const val PAGE = "page"

internal interface MovieService {

    @GET("popular")
    suspend fun fetchPopularMovies(
        @Query(PAGE) page: Int,
    ): FetchPopularMoviesResponse
}
