package com.ahmadfebrianto.moviecatalogue.ui.home.tvshows

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.databinding.RvBaseItemBinding
import com.ahmadfebrianto.moviecatalogue.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    class TvShowViewHolder(private val binding: RvBaseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Uri.parse(tvShow.poster))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.ivPoster)

                tvTitle.text = tvShow.title
                tvRating.text =
                    itemView.resources.getString(R.string.rating_placeholder, tvShow.rating)
                tvReleaseYear.text =
                    itemView.resources.getString(R.string.release_placeholder, tvShow.releaseYear)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tvShow.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TYPE_TV_SHOW)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowViewHolder {
        val baseRowBinding =
            RvBaseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(baseRowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }
}