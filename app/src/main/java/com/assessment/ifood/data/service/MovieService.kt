package com.assessment.ifood.data.service

import com.assessment.ifood.data.dto.GenreDTO
import com.assessment.ifood.data.dto.MovieDTO
import com.assessment.ifood.data.dto.PaginatedResponseDTO
import com.assessment.ifood.data.dto.TvSeriesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/popular")
    suspend fun fetchPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "pt-BR"
    ): PaginatedResponseDTO<MovieDTO>

    @GET("3/tv/popular")
    suspend fun fetchPopularTvSeries(
        @Query("page") page: Int,
        @Query("language") language: String = "pt-BR"
    ): PaginatedResponseDTO<TvSeriesDTO>

    @GET("3/genre/movie/list")
    suspend fun fetchGenres(
        @Query("language") language: String = "pt-BR"
    ): GenreDTO
}