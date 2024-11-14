package com.juan.carlos.flores.moviedb.presentation.navigation

import MovieDetailScreen
import MoviesListScreen
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.juan.carlos.flores.moviedb.presentation.login.LoginScreen
import com.juan.carlos.flores.moviedb.utils.getStartDestination

@Composable
fun AppNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = getStartDestination(),
        modifier = modifier,
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(
                onNavigateToMovies = {
                    navController.navigate(Screen.MoviesList.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = Screen.MoviesList.route,
            arguments = listOf(
                navArgument("screen_label"){
                    type = NavType.StringType
                    defaultValue = "Movies"
                }
            )
        ) {
            MoviesListScreen(
                onMovieClick = { movieId ->
                    navController.navigate(Screen.MovieDetail.createRoute(movieId))
                }
            )
        }
        composable(
            Screen.MovieDetail.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                },
                navArgument("screen_label") {
                    type = NavType.StringType
                    defaultValue = "Movie detail"
                }
            )
        ) {
            MovieDetailScreen(
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}