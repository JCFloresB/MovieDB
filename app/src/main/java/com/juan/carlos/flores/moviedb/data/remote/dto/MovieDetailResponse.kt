package com.juan.carlos.flores.moviedb.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val rating: Double,
    @SerializedName("runtime") val duration: Int,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("genres") val genres: List<GenreDto>,
    @SerializedName("overview") val description: String
)
