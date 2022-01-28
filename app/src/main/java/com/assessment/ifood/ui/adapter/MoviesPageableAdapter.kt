package com.assessment.ifood.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment.ifood.BuildConfig
import com.assessment.ifood.R
import com.assessment.ifood.databinding.LayoutMovieItemBinding
import com.assessment.ifood.domain.LoadType
import com.assessment.ifood.domain.Movie
import com.assessment.ifood.domain.Paginated
import com.assessment.ifood.extensions.toString
import com.squareup.picasso.Picasso

class MoviesPageableAdapter(
    loadNextPage: ((next: Int, pageSize: Int) -> Unit),
    val onClick: ((Movie) -> Unit)? = null
) : LazyPageableAdapter<MoviesPageableAdapter.PageHolder, Movie>(1, 3, loadNextPage) {
    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PageHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_item, parent, false)
    )

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.updateData(movies[position])
    }

    override fun getItemCount() = movies.size

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutMovieItemBinding.bind(view)
        lateinit var movie: Movie

        init {
            view.setOnClickListener { onClick?.invoke(movie) }
        }

        fun updateData(item: Movie) {
            movie = item
            binding.movie = item
            binding.releaseDate = item.releaseDate?.toString("dd/MM/yyyy")

            Picasso.get()
                .load(BuildConfig.API_IMAGE_URL + item.posterPath)
                .into(binding.ivPoster)
        }
    }

    override fun append(items: List<Movie>) {
        val position = this.movies.size
        this.movies.addAll(items)
        notifyItemRangeInserted(position, items.size)
    }

    override fun refresh(items: List<Movie>) {
        if (this.movies.isNotEmpty()) {
            val itemsSize = this.movies.size
            this.movies.clear()
            notifyItemRangeRemoved(0, itemsSize)
        }

        append(items)
    }

}