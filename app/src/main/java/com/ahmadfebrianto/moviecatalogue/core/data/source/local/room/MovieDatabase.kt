package com.ahmadfebrianto.moviecatalogue.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun catalogDao(): MovieDao

    companion object {
        const val MOVIE_TABLE_NAME = "movies"
        const val TV_SHOW_TABLE_NAME = "tvshows"
        private const val DB_NAME = "movie_catalog.db"

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    DB_NAME
                ).build().apply {
                    INSTANCE = this
                }
            }
        }
    }
}