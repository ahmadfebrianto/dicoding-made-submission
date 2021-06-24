package com.ahmadfebrianto.moviecatalogue.core.domain.usecase

import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import io.reactivex.Flowable

interface MovieUseCase {
    fun getAllMovies(): Flowable<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flowable<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}