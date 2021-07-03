package com.ahmadfebrianto.moviecatalogue.core.domain.repository

import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DomainRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}