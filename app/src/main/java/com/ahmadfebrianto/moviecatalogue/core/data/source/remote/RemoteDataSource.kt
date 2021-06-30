package com.ahmadfebrianto.moviecatalogue.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api.ApiResponse
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api.ApiService
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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
    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieList()
                val dataArray = response.movieList
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.movieList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}