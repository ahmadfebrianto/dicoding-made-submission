package com.ahmadfebrianto.moviecatalogue.core.domain.usecase

import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}