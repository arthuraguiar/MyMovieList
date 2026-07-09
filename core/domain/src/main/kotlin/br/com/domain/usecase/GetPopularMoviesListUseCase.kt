package br.com.domain.usecase

import br.com.domain.model.Movie
import br.com.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(page: Int): Flow<List<Movie>> {
        return movieRepository.getPopularMoviesList(page)
    }
}
