package com.ahmadfebrianto.moviecatalogue.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadfebrianto.moviecatalogue.core.ui.MovieAdapter
import com.ahmadfebrianto.moviecatalogue.core.ui.ViewModelFactory
import com.ahmadfebrianto.moviecatalogue.databinding.FragmentMoviesBinding

class FavoriteMovieFragment : Fragment() {

    private lateinit var favoriteMovieBinding: FragmentMoviesBinding
    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel
    private lateinit var viewModelFactory: ViewModelFactory
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
            viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            favoriteMovieViewModel =
                ViewModelProvider(this, viewModelFactory)[FavoriteMovieViewModel::class.java]

            favoriteMovieAdapter = MovieAdapter()
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