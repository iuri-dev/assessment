package com.assessment.ifood.data.repository.datasource

import com.assessment.ifood.domain.*
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    fun fetchPopularMovies(
        pageRequest: PageRequest,
        language: String
    ): Flow<ResponseWrapper<Paginated<Movie>>>

    fun fetchPopularTvSeries(
        pageRequest: PageRequest,
        language: String
    ): Flow<ResponseWrapper<Paginated<TvSeries>>>

    fun fetchGenres(language: String): Flow<ResponseWrapper<List<Genre>>>
}