package com.ahmadfebrianto.moviecatalogue.di

import com.ahmadfebrianto.moviecatalogue.core.domain.usecase.MovieInteractor
import com.ahmadfebrianto.moviecatalogue.core.domain.usecase.MovieUseCase
import com.ahmadfebrianto.moviecatalogue.detail.DetailMovieViewModel
import com.ahmadfebrianto.moviecatalogue.favorite.FavoriteMovieViewModel
import com.ahmadfebrianto.moviecatalogue.movies.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}