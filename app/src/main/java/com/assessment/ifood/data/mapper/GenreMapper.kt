package com.assessment.ifood.data.mapper

import com.assessment.ifood.data.dto.GenreDTO
import com.assessment.ifood.domain.Genre

object GenreMapper {
    fun dtoToDomain(dto: GenreDTO): List<Genre> =
        dto.genres.map { Genre(it.id, it.name) }
}