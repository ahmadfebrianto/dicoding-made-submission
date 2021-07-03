package com.ahmadfebrianto.moviecatalogue.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getAllMovies().asLiveData()
    }
}