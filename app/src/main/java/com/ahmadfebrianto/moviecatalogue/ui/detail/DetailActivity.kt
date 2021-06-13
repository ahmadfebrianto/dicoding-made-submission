package com.ahmadfebrianto.moviecatalogue.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityDetailBinding
import com.ahmadfebrianto.moviecatalogue.databinding.ContentActivityDetailBinding
import com.ahmadfebrianto.moviecatalogue.viewmodel.ViewModelFactory
import com.ahmadfebrianto.moviecatalogue.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV_SHOW = "tv_show"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var contentActivityDetailBinding: ContentActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail_page)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        contentActivityDetailBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val itemType = extras.getString(EXTRA_TYPE)
            val itemId = extras.getString(EXTRA_ID)
            if (itemType != null && itemId != null) {
                viewModel.setSelectedItem(itemId)
                when (itemType) {
                    TYPE_MOVIE -> {
                        viewModel.itemMovie.observe(this, { movie ->
                            if (movie != null) {
                                when (movie.status) {
                                    Status.LOADING -> activityDetailBinding.detailProgressBar.visibility =
                                        View.VISIBLE
                                    Status.SUCCESS -> {
                                        if (movie.data != null) {
                                            activityDetailBinding.detailProgressBar.visibility =
                                                View.GONE
                                            activityDetailBinding.content.visibility =
                                                View.VISIBLE
                                            activityDetailBinding.fabFavorite.visibility =
                                                View.VISIBLE
                                            populateMovieDetail(movie.data, viewModel)
                                        }
                                    }
                                    Status.ERROR -> {
                                        activityDetailBinding.detailProgressBar.visibility =
                                            View.GONE
                                        Toast.makeText(
                                            applicationContext,
                                            "Error loading Movie detail.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        })
                    }
                    TYPE_TV_SHOW -> {
                        viewModel.itemTvShow.observe(this, { tvShow ->
                            if (tvShow != null) {
                                when (tvShow.status) {
                                    Status.LOADING -> activityDetailBinding.detailProgressBar.visibility =
                                        View.VISIBLE
                                    Status.SUCCESS -> {
                                        if (tvShow.data != null) {
                                            activityDetailBinding.detailProgressBar.visibility =
                                                View.GONE
                                            activityDetailBinding.content.visibility =
                                                View.VISIBLE
                                            populateTvShowDetail(tvShow.data, viewModel)

                                            activityDetailBinding.fabFavorite.visibility =
                                                View.VISIBLE
                                        }
                                    }
                                    Status.ERROR -> {
                                        activityDetailBinding.detailProgressBar.visibility =
                                            View.GONE
                                        Toast.makeText(
                                            applicationContext,
                                            "Error loading Tv Show detail.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        })
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun populateMovieDetail(movie: MovieEntity, viewModel: DetailViewModel) {
        supportActionBar?.title = movie.title
        Glide.with(this)
            .load(Uri.parse(movie.poster))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(contentActivityDetailBinding.ivDtlPoster)
        contentActivityDetailBinding.tvDtlTitleValue.text = movie.title
        contentActivityDetailBinding.tvDtlRatingValue.text = movie.rating
        contentActivityDetailBinding.tvDtlReleaseYearValue.text = movie.releaseYear
        contentActivityDetailBinding.tvDtlDescriptionValue.text = movie.description

        if (movie.isFavorite) {
            activityDetailBinding.fabFavorite.setImageResource(R.drawable.ic_fav_filled)
        } else {
            activityDetailBinding.fabFavorite.setImageResource(R.drawable.ic_fav_unfilled)
        }

        activityDetailBinding.fabFavorite.setOnClickListener {
            viewModel.setFavoriteMovie()
            when (movie.isFavorite) {
                true -> showToast("Removed from Favorite Movie list")
                else -> showToast("Added to Favorite Movie list")
            }
        }
    }

    private fun populateTvShowDetail(tvShow: TvShowEntity, viewModel: DetailViewModel) {
        supportActionBar?.title = tvShow.title
        Glide.with(this)
            .load(Uri.parse(tvShow.poster))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(contentActivityDetailBinding.ivDtlPoster)
        contentActivityDetailBinding.tvDtlTitleValue.text = tvShow.title
        contentActivityDetailBinding.tvDtlRatingValue.text = tvShow.rating
        contentActivityDetailBinding.tvDtlReleaseYearValue.text = tvShow.releaseYear
        contentActivityDetailBinding.tvDtlDescriptionValue.text = tvShow.description

        if (tvShow.isFavorite) {
            activityDetailBinding.fabFavorite.setImageResource(R.drawable.ic_fav_filled)
        } else {
            activityDetailBinding.fabFavorite.setImageResource(R.drawable.ic_fav_unfilled)
        }

        activityDetailBinding.fabFavorite.setOnClickListener {
            viewModel.setFavoriteTvShow()
            when (tvShow.isFavorite) {
                true -> showToast("Removed from Favorite Tv Show list")
                else -> showToast("Added to Favorite Tv Show list")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}