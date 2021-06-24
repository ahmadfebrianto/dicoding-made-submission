package com.ahmadfebrianto.moviecatalogue.core.domain.usecase

import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.domain.repository.DomainRepository
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import io.reactivex.Flowable

class MovieInteractor(private val domainRepository: DomainRepository) : MovieUseCase {
    override fun getAllMovies(): Flowable<Resource<List<Movie>>> {
        return domainRepository.getAllMovies()
    }

    override fun getFavoriteMovies(): Flowable<List<Movie>> {
        return domainRepository.getFavoriteMovies()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return domainRepository.setFavoriteMovie(movie, state)
    }

}