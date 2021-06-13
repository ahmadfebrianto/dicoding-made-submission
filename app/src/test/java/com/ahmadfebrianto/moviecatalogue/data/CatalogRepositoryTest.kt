package com.ahmadfebrianto.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ahmadfebrianto.moviecatalogue.data.source.local.LocalDataSource
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.data.source.remote.RemoteDataSource
import com.ahmadfebrianto.moviecatalogue.utils.AppExecutors
import com.ahmadfebrianto.moviecatalogue.utils.LiveDataTestUtil
import com.ahmadfebrianto.moviecatalogue.utils.PagedListUtil
import com.ahmadfebrianto.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogRepository = FakeCatalogRepository(remote, local, appExecutors)

    private val dummyMovieResponses = DummyData.getMovies()
    private val dummyMovieResponse = dummyMovieResponses[0]
    private val dummyMovieResponseId = dummyMovieResponse.id

    private val dummyTvShowResponses = DummyData.getTvShows()
    private val dummyTvShowResponse = dummyTvShowResponses[0]
    private val dummyTvShowResponseId = dummyTvShowResponse.id


    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.getMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(dummyMovieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getAllTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.getTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(dummyTvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieById() {
        val dummyMovieEntity = MutableLiveData<MovieEntity>()
        dummyMovieEntity.value = DummyData.getMovieById(dummyMovieResponseId)
        Mockito.`when`(local.getMovieById(dummyMovieResponseId)).thenReturn(dummyMovieEntity)

        val movieEntity =
            LiveDataTestUtil.getValue(catalogRepository.getMovieById(dummyMovieResponseId))
        verify(local).getMovieById(dummyMovieResponseId)
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.data)
    }

    @Test
    fun getTvShowById() {
        val dummyTvShowEntity = MutableLiveData<TvShowEntity>()
        dummyTvShowEntity.value = DummyData.getTvShowById(dummyTvShowResponseId)
        Mockito.`when`(local.getTvShowById(dummyTvShowResponseId)).thenReturn(dummyTvShowEntity)

        val tvShowEntity =
            LiveDataTestUtil.getValue(catalogRepository.getTvShowById(dummyTvShowResponseId))
        verify(local).getTvShowById(dummyTvShowResponseId)
        assertNotNull(tvShowEntity)
        assertNotNull(tvShowEntity.data)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.getMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyMovieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.getTvShows()))
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(dummyTvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}