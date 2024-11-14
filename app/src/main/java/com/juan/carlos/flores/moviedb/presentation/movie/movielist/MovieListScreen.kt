import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.juan.carlos.flores.moviedb.presentation.custom.ErrorItem
import com.juan.carlos.flores.moviedb.presentation.movie.movielist.MoviesViewModel

@Composable
fun MoviesListScreen(
    onMovieClick: (Int) -> Unit,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

    when (movies.loadState.refresh) {
        is LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            ErrorItem(
                message = "Error loading movies",
                onRetry = { movies.retry() },
                isFullScreen = true
            )
        }

        is LoadState.NotLoading -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    count = movies.itemCount,
                    key = { index ->
                        movies[index]?.id ?: index
                    }
                ) { index ->
                    movies[index]?.let { movie ->
                        MovieItem(
                            movie = movie,
                            onClick = { onMovieClick(movie.id) }
                        )
                    }
                }

                // Paginación
                when (movies.loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    is LoadState.Error -> {
                        item {
                            ErrorItem(
                                message = "Error loading more movies",
                                onRetry = { movies.retry() }
                            )
                        }
                    }

                    is LoadState.NotLoading -> {
                        // No hacer nada
                    }
                }
            }

            /*LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        items(
            count = movies.itemCount,
            key = { index ->
                movies[index]?.id ?: index
            }
        ) { index ->
            movies[index]?.let { movie ->
                MovieItem(
                    movie = movie,
                    onClick = {
                        navController.navigate(Screen.MovieDetail.createRoute(movie.id))
                    }
                )
            }
        }

        when (movies.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    ErrorItem(
                        message = "Error loading movies",
                        onRetry = { movies.retry() }
                    )
                }
            }

            else -> {}
        }
    }

    when (movies.loadState.refresh) {
        is LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ErrorItem(
                    message = "Error loading movies",
                    onRetry = { movies.retry() }
                )
            }
        }

        else -> {}
    }*/
        }
    }
}
