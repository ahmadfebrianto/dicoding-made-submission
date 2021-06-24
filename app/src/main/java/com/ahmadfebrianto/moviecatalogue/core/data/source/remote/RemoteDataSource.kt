package com.ahmadfebrianto.moviecatalogue.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api.ApiResponse
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api.ApiService
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(service: ApiService): RemoteDataSource {
            return instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
        }
    }

    @SuppressLint("CheckResult")
    fun getAllMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        val resultMovies = PublishSubject.create<ApiResponse<List<MovieResponse>>>()
        val client = apiService.getMovieList()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val movieList = response.movieList
                resultMovies.onNext(
                    if (movieList.isNotEmpty()) ApiResponse.Success(movieList)
                    else ApiResponse.Empty
                )
            }, { error ->
                resultMovies.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultMovies.toFlowable(BackpressureStrategy.BUFFER)
    }
}