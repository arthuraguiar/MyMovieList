package br.com.mymovieslist.dataremote.network.di

import br.com.mymovieslist.dataremote.Secrets
import br.com.mymovieslist.dataremote.network.api.MovieService
import br.com.mymovieslist.dataremote.network.api.createMovieService
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

// defaultRequest { url { parameters.append(...) } } doesn't survive requests that set their own
// URL via url { takeFrom(...) } (which is exactly what Ktorfit-generated code does), since
// takeFrom replaces the whole URL builder state, wiping out anything defaultRequest set first.
// A plugin's onRequest hook runs after the request builder has already executed, so it does
// survive - this is Ktor's documented way to guarantee something is added to every request.
internal val ApiKeyPlugin = createClientPlugin("ApiKeyPlugin") {
    onRequest { request, _ ->
        request.url.parameters.append("api_key", Secrets.API_KEY)
    }
}

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                level = LogLevel.BODY
            }
            install(ApiKeyPlugin)
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
