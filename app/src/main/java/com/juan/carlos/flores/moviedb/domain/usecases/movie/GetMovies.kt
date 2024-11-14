package com.juan.carlos.flores.moviedb.domain.usecases.movie

import androidx.paging.PagingData
import com.juan.carlos.flores.moviedb.domain.model.Movie
import com.juan.carlos.flores.moviedb.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovies@Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMovieList().flowOn(Dispatchers.IO)
    }
}