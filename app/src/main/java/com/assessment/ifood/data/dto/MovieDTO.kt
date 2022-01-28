package com.assessment.ifood.data.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieDTO(
    var id: Long? = null,
    var title: String? = null,
    var overview: String? = null,
    var popularity: Float? = null,
    var adult: Boolean? = false,
    var video: Boolean? = false,

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Float? = null,
    @SerializedName("vote_count")
    var voteCount: Long? = null,
    @SerializedName("release_date")
    var releaseDate: Date? = null
)