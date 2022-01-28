package com.assessment.ifood.data.repository.datasource

import com.assessment.ifood.domain.Genre
import com.assessment.ifood.domain.Movie
import com.assessment.ifood.domain.Paginated
import com.assessment.ifood.domain.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    fun fetchPopularMovies(
        page: Int,
        language: String
    ): Flow<ResponseWrapper<Paginated<Movie>>>

    fun fetchGenres(language: String): Flow<ResponseWrapper<List<Genre>>>
}