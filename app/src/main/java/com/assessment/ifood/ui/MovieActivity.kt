package com.assessment.ifood.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assessment.ifood.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}