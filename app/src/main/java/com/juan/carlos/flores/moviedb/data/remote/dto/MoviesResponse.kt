package com.juan.carlos.flores.moviedb.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int
)