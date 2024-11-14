package com.juan.carlos.flores.moviedb.domain.usecases.login

import com.juan.carlos.flores.moviedb.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserLoggedIn @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.isLoggedIn()
    }
}