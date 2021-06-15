package com.ahmadfebrianto.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    private val itemId = MutableLiveData<String>()

    fun setSelectedItem(id: String) {
        this.itemId.value = id
    }

    var itemMovie: LiveData<MovieEntity> =
        Transformations.switchMap(itemId) { id ->
            catalogRepository.getMovieById(id)
        }

    var itemTvShow: LiveData<TvShowEntity> =
        Transformations.switchMap(itemId) { id ->
            catalogRepository.getTvShowById(id)
        }

    fun setFavoriteMovie() {
        val movie = itemMovie.value
        val newState = !itemMovie.value!!.isFavorite
        if (movie != null) {
            catalogRepository.setFavoriteMovie(movie, newState)
        }
    }

    fun setFavoriteTvShow() {
        val tvShow = itemTvShow.value
        val newState = !itemTvShow.value!!.isFavorite
        if (tvShow != null) {
            catalogRepository.setFavoriteTvShow(tvShow, newState)
        }
    }
}
