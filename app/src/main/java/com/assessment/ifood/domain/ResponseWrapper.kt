package com.assessment.ifood.domain

import kotlinx.coroutines.Job

sealed class ResponseWrapper<out T : Any> {
    data class Success<out T: Any>(val response: T) : ResponseWrapper<T>()
    data class Error(val error: Throwable) : ResponseWrapper<Nothing>()
    data class Loading(val job: Job? = null) : ResponseWrapper<Nothing>()
}
