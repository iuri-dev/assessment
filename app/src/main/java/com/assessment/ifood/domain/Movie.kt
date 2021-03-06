package com.assessment.ifood.domain

import java.io.Serializable
import java.util.*

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val popularity: Float,
    val adult: Boolean,
    val video: Boolean,
    val genreIds: List<Int>? = null,
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Long,
    val releaseDate: Date?
) : Serializable {
    var voteShow = voteAverage.toString()
    var posterImage = "w342/${posterPath}"
    var backdropImage = "w780/${backdropPath}"
}
