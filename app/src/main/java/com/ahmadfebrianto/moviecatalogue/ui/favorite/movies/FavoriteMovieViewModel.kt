package com.ahmadfebrianto.moviecatalogue.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        return catalogRepository.getFavoriteMovies()
    }
}