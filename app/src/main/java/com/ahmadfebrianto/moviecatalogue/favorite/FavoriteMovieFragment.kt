package com.ahmadfebrianto.moviecatalogue.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadfebrianto.moviecatalogue.core.ui.MovieAdapter
import com.ahmadfebrianto.moviecatalogue.databinding.FragmentMoviesBinding
import com.ahmadfebrianto.moviecatalogue.detail.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()
    private lateinit var favoriteMovieBinding: FragmentMoviesBinding
    private lateinit var favoriteMovieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteMovieBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return favoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            favoriteMovieAdapter = MovieAdapter()

            favoriteMovieAdapter.onItemClick = {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
                startActivity(intent)
            }

            favoriteMovieViewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
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
    }
}