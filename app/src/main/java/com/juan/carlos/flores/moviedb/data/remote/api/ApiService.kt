package com.juan.carlos.flores.moviedb.data.remote.api

import com.juan.carlos.flores.moviedb.data.remote.dto.MovieDetailResponse
import com.juan.carlos.flores.moviedb.data.remote.dto.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse
}