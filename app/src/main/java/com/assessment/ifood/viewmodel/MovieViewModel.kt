package com.assessment.ifood.viewmodel

import androidx.lifecycle.ViewModel
import com.assessment.ifood.data.repository.MovieRepository
import com.assessment.ifood.domain.Movie
import com.assessment.ifood.domain.PageRequest
import com.assessment.ifood.domain.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val loadedMovies = MutableStateFlow<List<Movie>>(listOf())

    fun fetchPopularMovies(pageRequest: PageRequest, language: String) =
        movieRepository.fetchPopularMovies(pageRequest, language)
            .onStart { emit(ResponseWrapper.Loading()) }
            .catch { emit(ResponseWrapper.Error(it)) }

    fun fetchPopularTvSeries(pageRequest: PageRequest, language: String) =
        movieRepository.fetchPopularTvSeries(pageRequest, language)
            .onStart { emit(ResponseWrapper.Loading()) }
            .catch { emit(ResponseWrapper.Error(it)) }

    fun fetchGenres(language: String) =
        movieRepository.fetchGenres(language)
            .onStart { emit(ResponseWrapper.Loading()) }
            .catch { emit(ResponseWrapper.Error(it)) }
}