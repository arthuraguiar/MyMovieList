package com.example.mymovieslist.data.network

import com.example.mymovieslist.data.model.FetchPopularMoviesResponse
import retrofit2.http.GET

interface MovieService {

    @GET("popular")
    suspend fun fetchPopularMovies(): FetchPopularMoviesResponse

}