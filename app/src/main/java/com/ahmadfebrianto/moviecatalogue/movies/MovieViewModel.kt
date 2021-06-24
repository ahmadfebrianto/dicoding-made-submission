package com.ahmadfebrianto.moviecatalogue.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.domain.usecase.MovieUseCase
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return LiveDataReactiveStreams.fromPublisher(movieUseCase.getAllMovies())
    }
}