package com.juan.carlos.flores.moviedb.domain.usecases.login

import com.example.poqueapi.domain.model.LogoutResult
import com.juan.carlos.flores.moviedb.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): LogoutResult {
        return repository.logout()
    }
}