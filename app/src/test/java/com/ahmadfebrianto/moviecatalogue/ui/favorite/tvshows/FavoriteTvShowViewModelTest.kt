package com.ahmadfebrianto.moviecatalogue.ui.favorite.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.DummyData
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
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
class FavoriteTvShowViewModelTest {

    private lateinit var favoriteTvShowViewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var favoriteTvShowObserver: Observer<PagedList<TvShowEntity>>

    @Before
    fun setUp() {
        favoriteTvShowViewModel = FavoriteTvShowViewModel(catalogRepository)
    }

    @Test
    fun getFavoriteTvShows() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = PagedTestDataSources.snapshot(DummyData.getTvShows())

        Mockito.`when`(catalogRepository.getFavoriteTvShows()).thenReturn(expected)

        favoriteTvShowViewModel.getFavoriteTvShows().observeForever(favoriteTvShowObserver)
        Mockito.verify(favoriteTvShowObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = favoriteTvShowViewModel.getFavoriteTvShows().value

        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun getEmptyFavoriteTvShows() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        Mockito.`when`(catalogRepository.getFavoriteTvShows()).thenReturn(expected)

        favoriteTvShowViewModel.getFavoriteTvShows().observeForever(favoriteTvShowObserver)
        Mockito.verify(favoriteTvShowObserver).onChanged(expected.value)

        val actualValueDataSize = favoriteTvShowViewModel.getFavoriteTvShows().value?.size
        assertTrue(actualValueDataSize == 0)
    }

    class PagedTestDataSources private constructor(private val items: List<TvShowEntity>) :
        PositionalDataSource<TvShowEntity>() {
        companion object {
            fun snapshot(items: List<TvShowEntity> = listOf()): PagedList<TvShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<TvShowEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}