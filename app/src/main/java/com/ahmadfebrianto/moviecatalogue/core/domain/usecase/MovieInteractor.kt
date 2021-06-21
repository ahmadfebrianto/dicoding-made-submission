package com.ahmadfebrianto.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import com.ahmadfebrianto.moviecatalogue.core.domain.model.Movie
import com.ahmadfebrianto.moviecatalogue.core.domain.repository.DomainRepository
import com.ahmadfebrianto.moviecatalogue.core.vo.Resource

class MovieInteractor(private val domainRepository: DomainRepository) : MovieUseCase {
    override fun getAllMovies(): LiveData<Resource<List<Movie>>> {
        return domainRepository.getAllMovies()
    }

    override fun getFavoriteMovies(): LiveData<List<Movie>> {
        return domainRepository.getFavoriteMovies()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return domainRepository.setFavoriteMovie(movie, state)
    }

}