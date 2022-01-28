package com.assessment.ifood.data.mapper

import com.assessment.ifood.data.dto.PaginatedResponseDTO
import com.assessment.ifood.domain.Paginated

object PaginatedMapper {
    fun <R, T> dtoToDomain(
        dto: PaginatedResponseDTO<R>,
        currentPage: Int,
        dataMapper: (R) -> T?
    ): Paginated<T> = Paginated(
        data = dto.results?.mapNotNull(dataMapper) ?: listOf(),
        page = currentPage,
        totalPages = dto.totalPages,
        totalResult = dto.totalResults
    )
}