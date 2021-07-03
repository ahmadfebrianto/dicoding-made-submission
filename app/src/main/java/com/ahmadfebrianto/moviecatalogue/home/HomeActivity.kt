package com.ahmadfebrianto.moviecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import com.ahmadfebrianto.moviecatalogue.core.ui.MovieAdapter
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityMainBinding
import com.ahmadfebrianto.moviecatalogue.detail.DetailMovieActivity
import com.ahmadfebrianto.moviecatalogue.favorite.FavoriteActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        movieAdapter = MovieAdapter()

        movieAdapter.onItemClick = {
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }

        movieViewModel.getMovies().observe(this, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        activityMainBinding.rvProgressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        activityMainBinding.rvProgressBar.visibility = View.GONE
                        movieAdapter.setData(movies.data!!)
                    }
                    is Resource.Error -> {
                        activityMainBinding.rvProgressBar.visibility = View.GONE
                        Toast.makeText(this, "Error loading movie list", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })

        with(activityMainBinding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
            setHasFixedSize(true)
            val dividerItemDecoration =
                DividerItemDecoration(
                    activityMainBinding.rvMovies.context,
                    DividerItemDecoration.VERTICAL
                )
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}