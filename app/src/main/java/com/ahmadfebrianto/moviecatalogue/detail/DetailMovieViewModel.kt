package com.ahmadfebrianto.moviecatalogue.detail

import androidx.lifecycle.ViewModel
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return movieUseCase.setFavoriteMovie(movie, state)
    }
}
