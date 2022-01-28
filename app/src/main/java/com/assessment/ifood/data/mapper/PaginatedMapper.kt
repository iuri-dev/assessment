package com.assessment.ifood.data.mapper

import com.assessment.ifood.data.dto.PaginatedResponseDTO
import com.assessment.ifood.domain.PageRequest
import com.assessment.ifood.domain.Paginated

object PaginatedMapper {
    fun <R, T> dtoToDomain(
        dto: PaginatedResponseDTO<R>,
        pageRequest: PageRequest,
        dataMapper: (R) -> T?
    ): Paginated<T> = Paginated(
        data = dto.results?.mapNotNull(dataMapper) ?: listOf(),
        page = pageRequest.page,
        loadType = pageRequest.loadType,
        totalPages = dto.totalPages,
        totalResult = dto.totalResults,
    )
}