package com.juan.carlos.flores.moviedb.presentation.navigation

sealed class Screen(
    val route: String
) {
    data object Login : Screen("login")
    data object MoviesList : Screen("movies?screen_label={screen_label}")
    data object MovieDetail : Screen("movie/{movieId}?screen_label={screen_label}") {
        fun createRoute(movieId: Int) = "movie/$movieId?screen_label=Movie Details"
    }
}