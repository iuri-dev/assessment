package com.assessment.ifood.extensions

import com.assessment.ifood.domain.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

fun <T : Any> flowWrapper(
    dispatcher: CoroutineContext = Dispatchers.IO,
    serviceCall: suspend () -> T
): Flow<ResponseWrapper<T>> = flow {

    val response = try {
        val serviceResponse = serviceCall.invoke()

        ResponseWrapper.Success(serviceResponse)
    } catch (ex: Throwable) {
        ResponseWrapper.Error(ex)
    }

    emit(response)
}.flowOn(dispatcher)

private fun responseException(ex: Exception) {

}