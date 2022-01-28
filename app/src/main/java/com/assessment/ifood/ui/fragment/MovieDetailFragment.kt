package com.assessment.ifood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assessment.ifood.BuildConfig
import com.assessment.ifood.databinding.FragmentMovieDetailBinding
import com.assessment.ifood.domain.Movie
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private val movie: Movie by lazy {
        (arguments?.getSerializable("movie") as Movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.movie = movie

        setupImage()

        return binding.root
    }

    private fun setupImage() {
        Picasso.get()
            .load(BuildConfig.API_IMAGE_URL + movie.backdropImage)
            .into(binding.ivBackdrop)
    }

    fun goBack() = findNavController().popBackStack()
}