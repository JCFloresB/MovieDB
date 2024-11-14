package com.juan.carlos.flores.moviedb.presentation.movie.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juan.carlos.flores.moviedb.domain.usecases.movie.GetMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getDetailUseCase: GetMovie,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("movieId")?.let { movieId ->
            loadMovieDetail(movieId)
        }
    }

    private fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _state.value = MovieDetailState.Loading
            try {
                getDetailUseCase(movieId)
                    .catch { e ->
                        _state.value = MovieDetailState.Error(e.message ?: "Unknown error occurred")
                    }
                    .collect { movie ->
                        _state.value = MovieDetailState.Success(movie)
                    }
            } catch (e: Exception) {
                _state.value = MovieDetailState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}