package com.assessment.ifood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.ifood.databinding.FragmentTvSeasonBinding
import com.assessment.ifood.domain.*
import com.assessment.ifood.extensions.collectResult
import com.assessment.ifood.extensions.setBlockState
import com.assessment.ifood.ui.adapter.TvSeriesPageableAdapter
import com.assessment.ifood.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvSeasonFragment : Fragment() {

    private lateinit var binding: FragmentTvSeasonBinding
    private val viewModel by viewModels<MovieViewModel>()

    private val seriesAdapter = TvSeriesPageableAdapter(::onLoadNext, ::onItemSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvSeasonBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.fetchPopularTvSeries(PageRequest(1, LoadType.REFRESH), "pt-BR")
                .collect(::collectTvSeries)
        }
    }

    private fun onLoadNext(next: Int) {
        lifecycleScope.launch {
            viewModel.fetchPopularTvSeries(PageRequest(next, LoadType.APPEND), "pt-BR")
                .collect(::collectTvSeries)
        }
    }

    private fun collectTvSeries(result: ResponseWrapper<Paginated<TvSeries>>) =
        collectResult(result, ::setLoading) { tvSeries ->
            seriesAdapter.load(tvSeries)
        }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = seriesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onItemSelected(tvSeries: TvSeries) {
        //detalhar serie
    }

    private fun setLoading(isLoading: Boolean) {
        //criar componente loading
        requireActivity().setBlockState(isLoading)
    }

}