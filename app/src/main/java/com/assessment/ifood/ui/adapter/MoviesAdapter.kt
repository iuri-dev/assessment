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
import com.squareup.picasso.Picasso

class MoviesAdapter(
    val onClick: ((Movie) -> Unit)? = null
) : RecyclerView.Adapter<MoviesAdapter.PageHolder>() {
    val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PageHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_item, parent, false)
    )

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val movie = movies[position]
        holder.movie = movie
        holder.binding.movie = movie

        Picasso.get()
            .load(BuildConfig.API_IMAGE_URL + movie.posterPath)
            .into(holder.binding.ivPoster)
    }

    override fun getItemCount() = movies.size

    fun load(result: Paginated<Movie>) {
        if (result.loadType == LoadType.REFRESH) {
            val size = movies.size
            movies.clear()
            notifyItemRangeRemoved(0, size)
        }

        movies.addAll(result.data)
        notifyItemRangeInserted(0, movies.size)
    }

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutMovieItemBinding.bind(view)
        lateinit var movie: Movie

        init {
            view.setOnClickListener { onClick?.invoke(movie) }
        }
    }
}