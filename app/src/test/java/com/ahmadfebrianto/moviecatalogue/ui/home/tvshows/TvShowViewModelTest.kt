package com.ahmadfebrianto.moviecatalogue.ui.home.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.DummyData
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.vo.Resource
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
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(catalogRepository)
    }

    @Test
    fun getTvShows() {
        val tvShows = PagedTestDataSources.snapshot(DummyData.getTvShows())
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        expected.value = Resource.success(tvShows)

        Mockito.`when`(catalogRepository.getAllTvShows()).thenReturn(expected)

        tvShowViewModel.getTvShows().observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = tvShowViewModel.getTvShows().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun getEmptyTvShows() {
        val tvShows = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        expected.value = Resource.success(tvShows)

        Mockito.`when`(catalogRepository.getAllTvShows()).thenReturn(expected)

        tvShowViewModel.getTvShows().observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(expected.value)

        val actualValueDataSize = tvShowViewModel.getTvShows().value?.data?.size
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