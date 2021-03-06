package com.ahmadfebrianto.moviecatalogue.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadfebrianto.moviecatalogue.core.R
import com.ahmadfebrianto.moviecatalogue.core.databinding.RvBaseItemBinding
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newList: List<Movie>) {
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RvBaseItemBinding.bind(itemView)
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185${movie.posterPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.bg_placeholder)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.ivPoster)

                tvTitle.text = movie.title
                tvRating.text =
                    itemView.resources.getString(R.string.rating_placeholder, movie.rating)
                tvReleaseDate.text =
                    itemView.resources.getString(R.string.release_placeholder, movie.releaseDate)

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_base_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listData[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return listData.size
    }
}