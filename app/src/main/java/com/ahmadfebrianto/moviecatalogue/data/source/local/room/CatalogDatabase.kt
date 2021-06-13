package com.ahmadfebrianto.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity


@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogDatabase : RoomDatabase() {

    abstract fun catalogDao(): CatalogDao

    companion object {
        const val MOVIE_TABLE_NAME = "movies"
        const val TV_SHOW_TABLE_NAME = "tvshows"
        private const val DB_NAME = "movie_catalog.db"

        @Volatile
        private var INSTANCE: CatalogDatabase? = null

        fun getInstance(context: Context): CatalogDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CatalogDatabase::class.java,
                    DB_NAME
                ).build().apply {
                    INSTANCE = this
                }
            }
        }
    }
}