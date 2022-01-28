package com.assessment.ifood.domain

import java.util.*

data class TvSeries(
    val id: Long,
    val title: String,
    val overview: String,
    val popularity: Float,
    val genreIds: List<Int>? = null,
    val country: List<String>? = null,
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Long,
    val firstAirDate: Date?
) {
    var posterImage = "w342/${posterPath}"
    var backdropImage = "w780/${backdropPath}"
}
