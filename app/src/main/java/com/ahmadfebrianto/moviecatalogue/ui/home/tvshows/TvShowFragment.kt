package com.ahmadfebrianto.moviecatalogue.ui.home.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadfebrianto.moviecatalogue.databinding.FragmentTvShowsBinding
import com.ahmadfebrianto.moviecatalogue.viewmodel.ViewModelFactory
import com.ahmadfebrianto.moviecatalogue.vo.Status

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowsBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, viewModelFactory)[TvShowViewModel::class.java]

            tvShowAdapter = TvShowAdapter()
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> {
                            fragmentTvShowBinding.rvProgressBar.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            fragmentTvShowBinding.rvProgressBar.visibility = View.GONE
                            tvShowAdapter.submitList(tvShows.data)
                        }
                        Status.ERROR -> {
                            fragmentTvShowBinding.rvProgressBar.visibility = View.GONE
                            Toast.makeText(
                                context,
                                "Error loading Tv Show list",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                adapter = tvShowAdapter
                setHasFixedSize(true)

                val dividerItemDecoration =
                    DividerItemDecoration(
                        fragmentTvShowBinding.rvTvShows.context,
                        DividerItemDecoration.VERTICAL
                    )
                addItemDecoration(dividerItemDecoration)
            }
        }
    }
}