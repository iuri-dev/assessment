package com.assessment.ifood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.ifood.databinding.FragmentMoviesBinding
import com.assessment.ifood.domain.*
import com.assessment.ifood.extensions.collectResult
import com.assessment.ifood.extensions.setBlockState
import com.assessment.ifood.ui.adapter.MoviesPageableAdapter
import com.assessment.ifood.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel by viewModels<MovieViewModel>()

    private val moviesAdapter = MoviesPageableAdapter(::onLoadNext, ::onItemSelected)

    val isLoading = MutableLiveData(false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.fetchPopularMovies(PageRequest(1, LoadType.REFRESH), "pt-BR")
                .collect(::collectMovies)
        }
    }

    private fun onLoadNext(next: Int) {
        if (this.isLoading.value == true) return
        lifecycleScope.launch {
            viewModel.fetchPopularMovies(PageRequest(next, LoadType.APPEND), "pt-BR")
                .collect(::collectMovies)
        }
    }

    private fun collectMovies(result: ResponseWrapper<Paginated<Movie>>) =
        collectResult(result, ::setLoading) { movies ->
            moviesAdapter.load(movies)
        }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onItemSelected(movie: Movie) {
        //detalhar movie
    }

    private fun setLoading(isLoading: Boolean) {
        //criar componente loading
        requireActivity().setBlockState(isLoading)
        this.isLoading.postValue(isLoading)
    }

}