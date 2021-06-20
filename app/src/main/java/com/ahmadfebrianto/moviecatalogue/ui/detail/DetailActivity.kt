package com.ahmadfebrianto.moviecatalogue.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityDetailBinding
import com.ahmadfebrianto.moviecatalogue.databinding.ContentActivityDetailBinding
import com.ahmadfebrianto.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
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
            val itemId = extras.getString(EXTRA_ID)
            if (itemId != null) {
                viewModel.setSelectedItem(itemId)
                viewModel.itemMovie.observe(this, { movie ->
                    if (movie != null) {
                        activityDetailBinding.detailProgressBar.visibility =
                            View.GONE
                        activityDetailBinding.content.visibility =
                            View.VISIBLE
                        activityDetailBinding.fabFavorite.visibility =
                            View.VISIBLE
                        populateMovieDetail(movie, viewModel)
                    }
                })
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
            .load(Uri.parse("https://image.tmdb.org/t/p/w342${movie.posterPath}"))
            .apply(
                RequestOptions.placeholderOf(R.drawable.bg_poster)
                    .error(R.drawable.ic_error)
            )
            .into(contentActivityDetailBinding.ivDetailPoster)

        Glide.with(this)
            .load(Uri.parse("https://image.tmdb.org/t/p/w780${movie.backdropPath}"))
            .apply(
                RequestOptions.placeholderOf(R.drawable.bg_backdrop)
                    .error(R.drawable.ic_error)
            )
            .into(contentActivityDetailBinding.ivDetailBackdrop)

        with(contentActivityDetailBinding) {
            tvDetailTitle.text = movie.title
            tvDetailRatingValue.text = movie.rating
            tvDetailLanguageValue.text = movie.language
            tvDetailReleaseValue.text = movie.releaseDate
            tvDetailDescriptionValue.text = movie.description
        }

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

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}