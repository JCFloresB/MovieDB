package com.juan.carlos.flores.moviedb.domain.repository

import com.example.poqueapi.domain.model.LoginResult
import com.example.poqueapi.domain.model.LogoutResult
import com.juan.carlos.flores.moviedb.domain.model.User

interface AuthRepository {

    suspend fun login(email: String, password: String): LoginResult
    suspend fun isLoggedIn(): Boolean
    suspend fun getCurrentUser(): User?
    suspend fun logout(): LogoutResult
}