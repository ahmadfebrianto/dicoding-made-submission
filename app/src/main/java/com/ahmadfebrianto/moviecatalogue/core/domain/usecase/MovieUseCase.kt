package com.ahmadfebrianto.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.vo.Resource

interface MovieUseCase {
    fun getAllMovies(): LiveData<Resource<List<Movie>>>
    fun getFavoriteMovies(): LiveData<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}