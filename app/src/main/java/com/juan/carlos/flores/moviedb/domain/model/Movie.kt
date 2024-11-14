package com.juan.carlos.flores.moviedb.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val rating: Double,
    val duration: Int,
    val releaseDate: String,
    val classification: String,
    val genres: List<String>,
    val description: String
)
