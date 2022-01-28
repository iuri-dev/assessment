package com.assessment.ifood.domain

enum class LoadType {
    APPEND,
    REFRESH
}

data class Paginated<T>(
    var data: List<T> = listOf(),
    var page: Int,
    var totalPages: Int? = null,
    var totalResult: Int? = null,
    var loadType: LoadType? = null
)