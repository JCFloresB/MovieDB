package com.juan.carlos.flores.moviedb.domain.usecases.movie

import com.juan.carlos.flores.moviedb.domain.model.Movie
import com.juan.carlos.flores.moviedb.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(id: Int): Flow<Movie> {
        return repository.getMovie(id).flowOn(Dispatchers.IO)
    }
}