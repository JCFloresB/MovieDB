package com.juan.carlos.flores.moviedb.presentation.movie.moviedetail

import com.juan.carlos.flores.moviedb.domain.model.Movie

sealed class MovieDetailState {
    data object Loading : MovieDetailState()
    data class Success(val movie: Movie) : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
}