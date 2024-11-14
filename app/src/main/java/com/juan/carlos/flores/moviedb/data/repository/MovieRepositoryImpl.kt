package com.juan.carlos.flores.moviedb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.juan.carlos.flores.moviedb.data.mapper.MovieMapper.toDomain
import com.juan.carlos.flores.moviedb.data.paging.MoviesPagingSource
import com.juan.carlos.flores.moviedb.data.remote.api.ApiService
import com.juan.carlos.flores.moviedb.domain.model.Movie
import com.juan.carlos.flores.moviedb.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class MovieRepositoryImpl @Inject constructor(
    private val api: ApiService,
    @Named("api_key") private val apiKey: String,
): MoviesRepository {
    override fun getMovieList(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 2
        ),
        pagingSourceFactory = { MoviesPagingSource(api, apiKey) }
    ).flow

    override fun getMovie(id: Int): Flow<Movie> = flow {
        val response = api.getMovieDetail(id, apiKey)
        emit(response.toDomain())
    }
}