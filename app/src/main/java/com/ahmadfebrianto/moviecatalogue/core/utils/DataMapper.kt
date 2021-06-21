package com.ahmadfebrianto.moviecatalogue.core.utils

import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(responses: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        responses.map {
            val movie = MovieEntity(
                id = it.id,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                title = it.title,
                description = it.description,
                language = it.language,
                rating = it.rating,
                releaseDate = it.releaseDate,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(entities: List<MovieEntity>): List<Movie> {
        return entities.map {
            Movie(
                id = it.id,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                title = it.title,
                description = it.description,
                language = it.language,
                rating = it.rating,
                releaseDate = it.releaseDate,
                isFavorite = it.isFavorite

            )
        }
    }

    fun mapDomainToEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id,
            backdropPath = movie.backdropPath,
            posterPath = movie.posterPath,
            title = movie.title,
            description = movie.description,
            language = movie.language,
            rating = movie.rating,
            releaseDate = movie.releaseDate,
            isFavorite = movie.isFavorite
        )
    }
}