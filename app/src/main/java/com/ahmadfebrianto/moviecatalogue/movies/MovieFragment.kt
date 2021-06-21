package com.ahmadfebrianto.moviecatalogue.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadfebrianto.moviecatalogue.core.ui.MovieAdapter
import com.ahmadfebrianto.moviecatalogue.core.ui.ViewModelFactory
import com.ahmadfebrianto.moviecatalogue.core.vo.Status
import com.ahmadfebrianto.moviecatalogue.databinding.FragmentMoviesBinding

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMoviesBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: ViewModelFactory
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
            viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()

            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> {
                            fragmentMovieBinding.rvProgressBar.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            fragmentMovieBinding.rvProgressBar.visibility = View.GONE
                            movieAdapter.setData(movies.data!!)
                        }
                        Status.ERROR -> {
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