package com.ahmadfebrianto.moviecatalogue.core.di

import android.content.Context
import com.ahmadfebrianto.moviecatalogue.core.data.MovieRepository
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.room.MovieDatabase
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api.ApiConfig
import com.ahmadfebrianto.moviecatalogue.core.domain.repository.DomainRepository
import com.ahmadfebrianto.moviecatalogue.core.domain.usecase.MovieInteractor
import com.ahmadfebrianto.moviecatalogue.core.domain.usecase.MovieUseCase
import com.ahmadfebrianto.moviecatalogue.core.utils.AppExecutors


object Injection {
    private fun provideRepository(context: Context): DomainRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}