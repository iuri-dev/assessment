package com.assessment.ifood.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assessment.ifood.domain.LoadType
import com.assessment.ifood.domain.Paginated

abstract class LazyPageableAdapter<T: RecyclerView.ViewHolder, R>(
    private val thresholdLoading: Int,
    private val loadNextPage: ((next: Int) -> Unit)
): RecyclerView.Adapter<T>() {

    private var currentPage: Int = 1
    private var next: Int? = null

    private val onRecyclerViewScroll = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager
            if (layoutManager !is LinearLayoutManager) return

            val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
            if (lastVisiblePosition == (itemCount - thresholdLoading))
                onThresholdReached()
        }
    }

    private fun onThresholdReached() {
        loadNextPage.invoke(next ?: return)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(onRecyclerViewScroll)
    }

    abstract fun append(items: List<R>)
    abstract fun refresh(items: List<R>)

    fun load(page: Paginated<R>) {
        when(page.loadType) {
            LoadType.APPEND -> append(page.data)
            LoadType.REFRESH -> refresh(page.data)
        }

        currentPage = page.page
        next = currentPage + 1

        if (currentPage == page.totalPages || page.totalPages == 0)
            next = null

        if (page.data.size <= 15 && currentPage == 1)
            loadNextPage.invoke(next ?: return)
    }

}