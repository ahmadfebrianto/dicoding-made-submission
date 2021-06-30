package com.ahmadfebrianto.moviecatalogue.core.domain.usecase

import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.domain.repository.DomainRepository

class MovieInteractor(private val domainRepository: DomainRepository) : MovieUseCase {
    override fun getAllMovies() = domainRepository.getAllMovies()

    override fun getFavoriteMovies() = domainRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return domainRepository.setFavoriteMovie(movie, state)
    }

}