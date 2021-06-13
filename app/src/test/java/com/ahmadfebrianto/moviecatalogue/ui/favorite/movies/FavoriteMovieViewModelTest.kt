package com.ahmadfebrianto.moviecatalogue.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.DummyData
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var favoriteMovieObserver: Observer<PagedList<MovieEntity>>

    @Before
    fun setUp() {
        favoriteMovieViewModel = FavoriteMovieViewModel(catalogRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSources.snapshot(DummyData.getMovies())

        Mockito.`when`(catalogRepository.getFavoriteMovies()).thenReturn(expected)

        favoriteMovieViewModel.getFavoriteMovies().observeForever(favoriteMovieObserver)
        Mockito.verify(favoriteMovieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = favoriteMovieViewModel.getFavoriteMovies().value

        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun getEmptyFavoriteMovies() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        Mockito.`when`(catalogRepository.getFavoriteMovies()).thenReturn(expected)

        favoriteMovieViewModel.getFavoriteMovies().observeForever(favoriteMovieObserver)
        Mockito.verify(favoriteMovieObserver).onChanged(expected.value)

        val actualValueDataSize = favoriteMovieViewModel.getFavoriteMovies().value?.size
        assertTrue(actualValueDataSize == 0)
    }

    class PagedTestDataSources private constructor(private val items: List<MovieEntity>) :
        PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<MovieEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}