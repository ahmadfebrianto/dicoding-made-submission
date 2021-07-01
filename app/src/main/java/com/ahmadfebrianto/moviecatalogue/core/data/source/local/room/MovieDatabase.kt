package com.ahmadfebrianto.moviecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val MOVIE_TABLE_NAME = "movies"
        const val DB_NAME = "movie_catalog.db"
    }
}