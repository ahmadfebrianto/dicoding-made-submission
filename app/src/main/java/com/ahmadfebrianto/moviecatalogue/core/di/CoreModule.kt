package com.ahmadfebrianto.moviecatalogue.core.di

import androidx.room.Room
import com.ahmadfebrianto.moviecatalogue.core.data.MovieRepository
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.room.MovieDatabase
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.ahmadfebrianto.moviecatalogue.core.data.source.remote.api.ApiService
import com.ahmadfebrianto.moviecatalogue.core.domain.repository.DomainRepository
import com.ahmadfebrianto.moviecatalogue.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            MovieDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/4/list/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<DomainRepository> { MovieRepository(get(), get(), get()) }
}