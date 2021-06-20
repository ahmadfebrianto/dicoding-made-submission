package com.ahmadfebrianto.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    private val itemId = MutableLiveData<String>()

    fun setSelectedItem(id: String) {
        this.itemId.value = id
    }

    var itemMovie: LiveData<MovieEntity> =
        Transformations.switchMap(itemId) { id ->
            catalogRepository.getMovieById(id)
        }

    fun setFavoriteMovie() {
        val movie = itemMovie.value
        val newState = !itemMovie.value!!.isFavorite
        if (movie != null) {
            catalogRepository.setFavoriteMovie(movie, newState)
        }
    }
}
