package com.ahmadfebrianto.moviecatalogue.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmadfebrianto.moviecatalogue.core.di.Injection
import com.ahmadfebrianto.moviecatalogue.core.domain.usecase.MovieUseCase
import com.ahmadfebrianto.moviecatalogue.detail.DetailMovieViewModel
import com.ahmadfebrianto.moviecatalogue.favorite.FavoriteMovieViewModel
import com.ahmadfebrianto.moviecatalogue.movies.MovieViewModel

class ViewModelFactory private constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieUseCase) as T
            }


            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}