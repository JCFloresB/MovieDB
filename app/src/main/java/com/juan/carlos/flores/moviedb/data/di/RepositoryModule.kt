package com.juan.carlos.flores.moviedb.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.firebase.auth.FirebaseAuth
import com.juan.carlos.flores.moviedb.data.remote.api.ApiService
import com.juan.carlos.flores.moviedb.data.repository.AuthRepositoryImp
import com.juan.carlos.flores.moviedb.data.repository.MovieRepositoryImpl
import com.juan.carlos.flores.moviedb.domain.repository.AuthRepository
import com.juan.carlos.flores.moviedb.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: ApiService,
        @Named("api_key") apiKey: String
    ): MoviesRepository {
        return MovieRepositoryImpl(api, apiKey)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        dataStore: DataStore<Preferences>
    ): AuthRepository {
        return AuthRepositoryImp(firebaseAuth = firebaseAuth, store = dataStore)
    }
}