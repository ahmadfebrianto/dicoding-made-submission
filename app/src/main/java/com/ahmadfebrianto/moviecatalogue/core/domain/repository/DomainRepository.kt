package com.ahmadfebrianto.moviecatalogue.core.domain.repository

import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import io.reactivex.Flowable

interface DomainRepository {
    fun getAllMovies(): Flowable<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flowable<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}