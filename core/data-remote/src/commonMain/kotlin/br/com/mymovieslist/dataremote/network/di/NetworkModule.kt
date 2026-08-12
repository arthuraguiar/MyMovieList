package br.com.mymovieslist.dataremote.network.di

import br.com.mymovieslist.dataremote.Secrets
import br.com.mymovieslist.dataremote.network.api.MovieService
import br.com.mymovieslist.dataremote.network.api.createMovieService
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                level = LogLevel.BODY
            }
            defaultRequest {
                url {
                    parameters.append("api_key", Secrets.API_KEY)
                }
            }
        }
    }
    single {
        Ktorfit.Builder()
            .baseUrl(Secrets.API_URL)
            .httpClient(get<HttpClient>())
            .build()
    }
    single<MovieService> { get<Ktorfit>().createMovieService() }
}
