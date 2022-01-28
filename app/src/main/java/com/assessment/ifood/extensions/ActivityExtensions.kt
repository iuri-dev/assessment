package com.assessment.ifood.extensions

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.assessment.ifood.databinding.LayoutErrorSnackbarBinding
import com.google.android.material.snackbar.Snackbar

fun Activity.errorSnackBar(message: String, duration: Int) {
    val rootView = findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
    errorSnackBar(rootView, message, duration)
}

fun Activity.errorSnackBar(view: View, message: String, duration: Int) {
    val snackbar = Snackbar.make(view, "", duration)

    val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
    snackbarLayout.setBackgroundColor(getColor(android.R.color.transparent))

    val binding = LayoutErrorSnackbarBinding.inflate(layoutInflater)
    binding.text = message
    snackbarLayout.addView(binding.root, 0)

    snackbar.show()
}

fun Activity.setBlockState(isLoading: Boolean) {
    if (isLoading) blockInput() else unblockInput()
}

fun Activity.blockInput() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Activity.unblockInput() =
    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)