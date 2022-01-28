package com.assessment.ifood.data.mapper

import com.assessment.ifood.data.dto.TvSeriesDTO
import com.assessment.ifood.domain.TvSeries

object TvSeriesMapper {
    fun dtoToDomain(dto: TvSeriesDTO) = TvSeries(
        id = dto.id ?: Long.MIN_VALUE,
        title = dto.name ?: "",
        overview = dto.overview ?: "",
        popularity = dto.popularity ?: Float.MIN_VALUE,
        genreIds = dto.genreIds,
        country = dto.country,
        originalLanguage = dto.originalLanguage ?: "",
        originalTitle = dto.originalTitle ?: "",
        backdropPath = dto.backdropPath ?: "",
        posterPath = dto.posterPath ?: "",
        voteAverage = dto.voteAverage ?: Float.MIN_VALUE,
        voteCount = dto.voteCount ?: Long.MIN_VALUE,
        firstAirDate = dto.firstAirDate,
    )
}