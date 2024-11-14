import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.juan.carlos.flores.moviedb.presentation.movie.moviedetail.MovieDetailState
import com.juan.carlos.flores.moviedb.presentation.movie.moviedetail.MovieDetailViewModel
import com.juan.carlos.flores.moviedb.presentation.movie.moviedetail.components.ErrorContent
import com.juan.carlos.flores.moviedb.presentation.movie.moviedetail.components.LoadingIndicator
import com.juan.carlos.flores.moviedb.presentation.movie.moviedetail.components.MovieDetailContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun MovieDetailScreen(
    onNavigateUp:() -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        when (state) {
            is MovieDetailState.Loading -> {
                LoadingIndicator()
            }

            is MovieDetailState.Success -> {
                val movie = (state as MovieDetailState.Success).movie
                MovieDetailContent(
                    movie = movie,
                    onNavigateUp = onNavigateUp,
                    )
            }

            is MovieDetailState.Error -> {
                ErrorContent(
                    message = (state as MovieDetailState.Error).message,
                    onRetry = { /* Implementar retry si es necesario */ }
                )
            }
        }
    }
}
//}
