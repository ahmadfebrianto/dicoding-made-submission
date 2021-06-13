package com.ahmadfebrianto.moviecatalogue.ui.home.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.vo.Resource

class TvShowViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return catalogRepository.getAllTvShows()
    }
}