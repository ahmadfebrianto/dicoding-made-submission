package com.ahmadfebrianto.moviecatalogue.ui.favorite.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadfebrianto.moviecatalogue.databinding.FragmentTvShowsBinding
import com.ahmadfebrianto.moviecatalogue.ui.home.tvshows.TvShowAdapter
import com.ahmadfebrianto.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {

    private lateinit var favoriteTvShowBinding: FragmentTvShowsBinding
    private lateinit var favoriteTvShowViewModel: FavoriteTvShowViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var favoriteTvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteTvShowBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return favoriteTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModelFactory = ViewModelFactory.getInstance(requireActivity())
            favoriteTvShowViewModel =
                ViewModelProvider(this, viewModelFactory)[FavoriteTvShowViewModel::class.java]

            favoriteTvShowAdapter = TvShowAdapter()
            favoriteTvShowViewModel.getFavoriteTvShows().observe(
                viewLifecycleOwner, { tvShows ->
                    favoriteTvShowAdapter.submitList(tvShows)
                    if (tvShows.isNullOrEmpty()) {
                        favoriteTvShowBinding.tvTvshowsEmpty.visibility = View.VISIBLE
                    }
                }
            )

            with(favoriteTvShowBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                adapter = favoriteTvShowAdapter
                setHasFixedSize(true)

                val dividerItemDecoration =
                    DividerItemDecoration(
                        favoriteTvShowBinding.rvTvShows.context,
                        DividerItemDecoration.VERTICAL
                    )
                addItemDecoration(dividerItemDecoration)
            }
        }
    }
}