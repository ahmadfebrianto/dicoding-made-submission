package com.ahmadfebrianto.moviecatalogue.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityDetailBinding
import com.ahmadfebrianto.moviecatalogue.databinding.ContentActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
        const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var contentActivityDetailBinding: ContentActivityDetailBinding
    private val detailViewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail_movie)

        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        contentActivityDetailBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)

        val movieItem = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movieItem != null) {
            populateMovieDetail(movieItem)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun populateMovieDetail(movie: Movie) {
        supportActionBar?.title = movie.title

        with(activityDetailBinding) {
            detailProgressBar.visibility = View.GONE
            content.visibility = View.VISIBLE
            fabFavorite.visibility = View.VISIBLE

            var isFavorite = movie.isFavorite
            setFavoriteIcon(isFavorite)
            fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                detailViewModel.setFavoriteMovie(movie, isFavorite)
                showMessage(isFavorite)
                setFavoriteIcon(isFavorite)
            }
        }

        with(contentActivityDetailBinding) {
            setImage(posterBaseUrl + movie.posterPath, ivDetailPoster)
            setImage(backdropBaseUrl + movie.backdropPath, ivDetailBackdrop)
            tvDetailTitle.text = movie.title
            tvDetailRatingValue.text = movie.rating
            tvDetailLanguageValue.text = movie.language
            tvDetailReleaseValue.text = movie.releaseDate
            tvDetailDescriptionValue.text = movie.description
        }
    }

    private fun setFavoriteIcon(state: Boolean) {
        with(activityDetailBinding) {
            when (state) {
                true -> fabFavorite.setImageResource(R.drawable.ic_fav_filled)
                else -> fabFavorite.setImageResource(R.drawable.ic_fav_unfilled)
            }
        }
    }

    private fun setImage(imagePath: String, target: ImageView) {
        Glide.with(this)
            .load(imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.bg_placeholder).error(R.drawable.ic_error)
            )
            .into(target)
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun showMessage(state: Boolean) {
        when (state) {
            true -> showToast("Movie added to favorite list")
            else -> showToast("Movie removed from favorite list")
        }
    }
}