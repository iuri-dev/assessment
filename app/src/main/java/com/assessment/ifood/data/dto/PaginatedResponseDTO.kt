package com.assessment.ifood.data.dto

import com.google.gson.annotations.SerializedName

data class PaginatedResponseDTO<T>(
    var results: List<T>? = null,
    var page: Int? = null,

    @SerializedName("total_pages")
    var totalPages: Int? = null,

    @SerializedName("total_results")
    var totalResults: Int? = null,
)
