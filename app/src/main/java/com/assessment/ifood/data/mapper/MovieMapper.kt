package com.assessment.ifood.data.mapper

import com.assessment.ifood.data.dto.MovieDTO
import com.assessment.ifood.domain.Movie

object MovieMapper {
    fun dtoToDomain(dto: MovieDTO) = Movie(
        id = dto.id ?: Long.MIN_VALUE,
        title = dto.title ?: "",
        overview = dto.overview ?: "",
        popularity = dto.popularity ?: Float.MIN_VALUE,
        adult = dto.adult ?: false,
        video = dto.video ?: false,
        genreIds = dto.genreIds,
        originalLanguage = dto.originalLanguage ?: "",
        originalTitle = dto.originalTitle ?: "",
        backdropPath = dto.backdropPath ?: "",
        posterPath = dto.posterPath ?: "",
        voteAverage = dto.voteAverage ?: Float.MIN_VALUE,
        voteCount = dto.voteCount ?: Long.MIN_VALUE,
        releaseDate = dto.releaseDate,
    )
}