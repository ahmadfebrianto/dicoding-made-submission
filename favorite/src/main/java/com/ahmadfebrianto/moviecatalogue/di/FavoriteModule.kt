package com.ahmadfebrianto.moviecatalogue.di

import com.ahmadfebrianto.moviecatalogue.favorite.FavoriteMovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModules = module {
    viewModel { FavoriteMovieViewModel(get()) }
}