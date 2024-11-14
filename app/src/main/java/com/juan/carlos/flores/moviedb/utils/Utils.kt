package com.juan.carlos.flores.moviedb.utils

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.juan.carlos.flores.moviedb.presentation.navigation.Screen

fun getStartDestination(): String {
    return if (Firebase.auth.currentUser != null) {
        Screen.MoviesList.route
    } else {
        Screen.Login.route
    }
}