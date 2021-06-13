package com.ahmadfebrianto.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.vo.Resource

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    private val itemId = MutableLiveData<String>()

    fun setSelectedItem(id: String) {
        this.itemId.value = id
    }

    var itemMovie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(itemId) { id ->
            catalogRepository.getMovieById(id)
        }

    var itemTvShow: LiveData<Resource<TvShowEntity>> =
        Transformations.switchMap(itemId) { id ->
            catalogRepository.getTvShowById(id)
        }

    fun setFavoriteMovie() {
        val movie = itemMovie.value?.data!!
        val newState = !itemMovie.value?.data!!.isFavorite
        catalogRepository.setFavoriteMovie(movie, newState)
    }

    fun setFavoriteTvShow() {
        val tvShow = itemTvShow.value?.data!!
        val newState = !itemTvShow.value?.data!!.isFavorite
        catalogRepository.setFavoriteTvShow(tvShow, newState)
    }
}
