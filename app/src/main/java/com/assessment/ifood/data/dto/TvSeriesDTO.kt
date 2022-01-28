package com.assessment.ifood.data.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class TvSeriesDTO(
    var id: Long? = null,
    var name: String? = null,
    var overview: String? = null,
    var popularity: Float? = null,

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("origin_country")
    var country: List<String>? = null,
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_name")
    var originalTitle: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Float? = null,
    @SerializedName("vote_count")
    var voteCount: Long? = null,
    @SerializedName("first_air_date")
    var firstAirDate: Date? = null
)