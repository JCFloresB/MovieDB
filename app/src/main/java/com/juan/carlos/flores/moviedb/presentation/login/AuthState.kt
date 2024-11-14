package com.juan.carlos.flores.moviedb.presentation.login

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
}