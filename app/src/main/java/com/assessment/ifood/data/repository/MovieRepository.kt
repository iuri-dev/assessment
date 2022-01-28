package com.assessment.ifood.data.repository

import com.assessment.ifood.data.mapper.GenreMapper
import com.assessment.ifood.data.mapper.MovieMapper
import com.assessment.ifood.data.mapper.PaginatedMapper
import com.assessment.ifood.data.repository.datasource.MovieDataSource
import com.assessment.ifood.data.service.MovieService
import com.assessment.ifood.extensions.flowWrapper
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) : MovieDataSource {

    override fun fetchPopularMovies(page: Int, language: String) = flowWrapper {
        val dto = movieService.fetchPopularMovies(page, language)

        PaginatedMapper.dtoToDomain(dto, page, MovieMapper::dtoToDomain)
    }

    override fun fetchGenres(language: String) = flowWrapper {
        val dto = movieService.fetchGenres(language)

        GenreMapper.dtoToDomain(dto)
    }

}