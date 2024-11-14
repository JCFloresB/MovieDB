package com.juan.carlos.flores.moviedb.data.mapper

import com.juan.carlos.flores.moviedb.data.remote.dto.MovieDetailResponse
import com.juan.carlos.flores.moviedb.data.remote.dto.MovieDto
import com.juan.carlos.flores.moviedb.domain.model.Movie

object MovieMapper {
    fun MovieDto.toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            posterPath = posterPath,
            rating = rating,
            // Estos campos solo están en el detalle, así que proporcionamos valores por defecto
            duration = 0,
            releaseDate = "",
            classification = "",
            genres = emptyList(),
            description = ""
        )
    }

    fun MovieDetailResponse.toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            posterPath = posterPath,
            rating = rating,
            duration = duration,
            releaseDate = releaseDate,
            classification = if (adult) "Adult" else "All Ages",
            genres = genres.map { it.name },
            description = description
        )
    }
}