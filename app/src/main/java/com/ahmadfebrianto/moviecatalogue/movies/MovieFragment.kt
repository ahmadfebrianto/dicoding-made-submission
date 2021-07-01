package com.ahmadfebrianto.moviecatalogue.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import com.ahmadfebrianto.moviecatalogue.core.ui.MovieAdapter
import com.ahmadfebrianto.moviecatalogue.databinding.FragmentMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var fragmentMovieBinding: FragmentMoviesBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            movieAdapter = MovieAdapter()
            movieViewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            fragmentMovieBinding.rvProgressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            fragmentMovieBinding.rvProgressBar.visibility = View.GONE
                            movieAdapter.setData(movies.data!!)
                        }
                        is Resource.Error -> {
                            fragmentMovieBinding.rvProgressBar.visibility = View.GONE
                            Toast.makeText(context, "Error loading movie list", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })

            with(fragmentMovieBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
                setHasFixedSize(true)
                val dividerItemDecoration =
                    DividerItemDecoration(
                        fragmentMovieBinding.rvMovies.context,
                        DividerItemDecoration.VERTICAL
                    )
                addItemDecoration(dividerItemDecoration)
            }
        }
    }
}