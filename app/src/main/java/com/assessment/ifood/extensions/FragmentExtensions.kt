package com.assessment.ifood.extensions

import androidx.fragment.app.Fragment
import com.assessment.ifood.R
import com.assessment.ifood.domain.ResponseWrapper

fun <T : Any> Fragment.collectResult(
    result: ResponseWrapper<T>,
    onLoadingState: ((Boolean) -> Unit)? = null,
    onSuccess: (T) -> Unit
) {
    when (result) {
        is ResponseWrapper.Success -> {
            onSuccess.invoke(result.response)
            onLoadingState?.invoke(false)
        }
        is ResponseWrapper.Error -> {
            onLoadingState?.invoke(false)
        }
        is ResponseWrapper.Loading -> onLoadingState?.invoke(true)
    }
}

fun Fragment.showDefaultError() =
    messageError(getString(R.string.connection_error_general))

fun Fragment.messageError(message: String) =
    requireActivity().errorSnackBar(message, 5000)