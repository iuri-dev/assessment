package com.assessment.ifood.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment.ifood.BuildConfig
import com.assessment.ifood.R
import com.assessment.ifood.databinding.LayoutSeriesItemBinding
import com.assessment.ifood.domain.TvSeries
import com.assessment.ifood.extensions.toString
import com.squareup.picasso.Picasso

class TvSeriesPageableAdapter(
    loadNextPage: ((next: Int) -> Unit),
    val onClick: ((TvSeries) -> Unit)? = null
) : LazyPageableAdapter<TvSeriesPageableAdapter.PageHolder, TvSeries>(3, loadNextPage) {
    private val tvSeries = mutableListOf<TvSeries>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PageHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_series_item, parent, false)
    )

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val tvSerie = tvSeries[position]
        holder.serie = tvSerie
        holder.binding.tvSerie = tvSerie
        holder.binding.firstAirDate = String.format(
            holder.itemView.context.getString(R.string.first_air_date),
            tvSerie.firstAirDate?.toString("dd/MM/yyyy")
        )

        Picasso.get()
            .load(BuildConfig.API_IMAGE_URL + tvSerie.posterImage)
            .into(holder.binding.ivPoster)
    }

    override fun getItemCount() = tvSeries.size

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutSeriesItemBinding.bind(view)
        lateinit var serie: TvSeries

        init {
            view.setOnClickListener { onClick?.invoke(serie) }
        }
    }

    override fun append(items: List<TvSeries>) {
        val position = this.tvSeries.size
        this.tvSeries.addAll(items)
        notifyItemRangeInserted(position, items.size)
    }

    override fun refresh(items: List<TvSeries>) {
        if (this.tvSeries.isNotEmpty()) {
            val itemsSize = this.tvSeries.size
            this.tvSeries.clear()
            notifyItemRangeRemoved(0, itemsSize)
        }

        append(items)
    }

}