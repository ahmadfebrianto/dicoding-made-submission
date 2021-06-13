package com.ahmadfebrianto.moviecatalogue.ui.favorite.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        return catalogRepository.getFavoriteTvShows()
    }
}