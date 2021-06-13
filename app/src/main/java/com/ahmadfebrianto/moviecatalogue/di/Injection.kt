package com.ahmadfebrianto.moviecatalogue.di

import android.content.Context
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.data.source.local.room.CatalogDatabase
import com.ahmadfebrianto.moviecatalogue.data.source.remote.RemoteDataSource
import com.ahmadfebrianto.moviecatalogue.utils.AppExecutors
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper


object Injection {
    fun provideRepository(context: Context): CatalogRepository {

        val database = CatalogDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(CatalogHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        val appExecutors = AppExecutors()

        return CatalogRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}