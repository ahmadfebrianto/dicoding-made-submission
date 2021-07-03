package com.ahmadfebrianto.moviecatalogue.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.core.ui.MovieAdapter
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityFavoriteBinding
import com.ahmadfebrianto.moviecatalogue.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()
    private lateinit var favoriteMovieBinding: ActivityFavoriteBinding
    private lateinit var favoriteMovieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteMovieBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.favorite_movies)

        favoriteMovieAdapter = MovieAdapter()
        favoriteMovieAdapter.onItemClick = {
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }

        favoriteMovieViewModel.getFavoriteMovies().observe(this, { movies ->
            favoriteMovieAdapter.setData(movies)
            if (movies.isNullOrEmpty()) {
                favoriteMovieBinding.tvMoviesEmpty.visibility = View.VISIBLE
            } else {
                favoriteMovieBinding.tvMoviesEmpty.visibility = View.INVISIBLE
            }
        })

        with(favoriteMovieBinding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteMovieAdapter
            setHasFixedSize(true)
            val dividerItemDecoration =
                DividerItemDecoration(
                    favoriteMovieBinding.rvMovies.context,
                    DividerItemDecoration.VERTICAL
                )
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}