package br.com.mymovieslist.dataremote.network.di

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import br.com.mymovieslist.dataremote.network.api.MovieService
import br.com.mymovieslist.dataremote.network.api.createMovieService
import kotlin.test.Test
import kotlin.test.assertTrue

internal class NetworkModuleTest {

    @Test
    fun `api_key survives Ktorfit's url takeFrom instead of being wiped by defaultRequest`() = runTest {
        var requestedUrl: String? = null
        val mockEngine = MockEngine { request ->
            requestedUrl = request.url.toString()
            respond(
                content = """{"results":[]}""",
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(ApiKeyPlugin)
        }

        val ktorfit = Ktorfit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .httpClient(client)
            .build()

        val service: MovieService = ktorfit.createMovieService()
        service.fetchPopularMovies(1)

        assertTrue(
            requestedUrl?.contains("api_key=") == true,
            "Expected the request URL to contain api_key, but was: $requestedUrl"
        )
    }
}
