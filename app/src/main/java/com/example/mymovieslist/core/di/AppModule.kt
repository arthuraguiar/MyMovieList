package com.example.mymovieslist.core.di

import com.example.mymovieslist.BuildConfig
import com.example.mymovieslist.core.resource.ResourceProvider
import com.example.mymovieslist.data.datasource.MoviesDataSource
import com.example.mymovieslist.data.datasource.MoviesDataSourceImpl
import com.example.mymovieslist.data.network.MovieService
import com.example.mymovieslist.data.network.interceptor.AuthInterceptor
import com.example.mymovieslist.data.repository.MovieRepositoryImpl
import com.example.mymovieslist.domain.mapper.MovieMapper
import com.example.mymovieslist.domain.repository.MovieRepository
import com.example.mymovieslist.domain.usecase.GetPopularMoviesListUseCase
import com.example.mymovieslist.presentation.MainViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalSerializationApi
val appModule = module {

    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideRetrofit(get()) }
    factory { provideMovieService(get()) }

    factory { ResourceProvider(get()) }

    factory<MoviesDataSource> {
        MoviesDataSourceImpl(get())
    }

    factory { MovieMapper(get()) }

    factory<MovieRepository> {
        MovieRepositoryImpl(moviesDataSource = get(), movieMapper = get())
    }
    factory {
        GetPopularMoviesListUseCase(
            movieRepository = get()
        )
    }

    viewModel {
        MainViewModel(get())
    }
}

@ExperimentalSerializationApi
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val contentType = MediaType.get("application/json")
    val json = Json { ignoreUnknownKeys = true }
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType)).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideMovieService(retrofit: Retrofit): MovieService =
    retrofit.create(MovieService::class.java)
