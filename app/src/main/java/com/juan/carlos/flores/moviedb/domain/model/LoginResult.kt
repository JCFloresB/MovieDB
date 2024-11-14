package com.example.poqueapi.domain.model

import com.juan.carlos.flores.moviedb.domain.model.User

sealed class LoginResult {
    data class Success(val user: User): LoginResult()
    data class Error(val message: String): LoginResult()
}