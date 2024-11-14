package com.juan.carlos.flores.moviedb.domain.repository

import androidx.paging.PagingData
import com.juan.carlos.flores.moviedb.domain.model.Movie
import com.juan.carlos.flores.moviedb.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovieList(): Flow<PagingData<Movie>>

    fun getMovie(id: Int): Flow<Movie>
//    fun getMovie(id: Int): Flow<Result<Movie>>
}