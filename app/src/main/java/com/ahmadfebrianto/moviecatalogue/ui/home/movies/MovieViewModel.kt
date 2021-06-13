package com.ahmadfebrianto.moviecatalogue.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.vo.Resource

class MovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return catalogRepository.getAllMovies()
    }
}