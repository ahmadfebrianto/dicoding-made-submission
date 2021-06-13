package com.ahmadfebrianto.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.CatalogResponse
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper
import com.ahmadfebrianto.moviecatalogue.utils.EspressoIdlingResource

class RemoteDataSource private constructor(private val catalogHelper: CatalogHelper) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 1000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: CatalogHelper): RemoteDataSource {
            return instance ?: synchronized(this) {
                RemoteDataSource(helper).apply {
                    instance = this
                }
            }
        }
    }


    /*MOVIES*/

    fun getAllMovies(): LiveData<ApiResponse<List<CatalogResponse>>> {
        EspressoIdlingResource.increment()

        val resultMovies = MutableLiveData<ApiResponse<List<CatalogResponse>>>()
        handler.postDelayed(
            {
                resultMovies.value = ApiResponse.success(catalogHelper.getMovies())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultMovies
    }

    fun getMovieById(movieId: String): LiveData<ApiResponse<CatalogResponse>> {
        EspressoIdlingResource.increment()

        val resultMovie = MutableLiveData<ApiResponse<CatalogResponse>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(catalogHelper.getMovieById(movieId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }


    /*TV SHOWS*/

    fun getAllTvShows(): LiveData<ApiResponse<List<CatalogResponse>>> {
        EspressoIdlingResource.increment()

        val resultTvShows = MutableLiveData<ApiResponse<List<CatalogResponse>>>()
        handler.postDelayed(
            {
                resultTvShows.value = ApiResponse.success(catalogHelper.getTvShows())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultTvShows
    }

    fun getTvShowById(tvShowId: String): LiveData<ApiResponse<CatalogResponse>> {
        EspressoIdlingResource.increment()

        val resultTvShow = MutableLiveData<ApiResponse<CatalogResponse>>()
        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(catalogHelper.getTvShowById(tvShowId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTvShow
    }


}