package com.assessment.ifood.data.dto

data class GenreDTO(
    var genres: List<GenreDetailDTO>
) {
    data class GenreDetailDTO(
        var id: Long,
        var name: String
    )
}
