package com.ahmadfebrianto.moviecatalogue.ui.home.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.DummyData
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
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
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(catalogRepository)
    }


    @Test
    fun getMovies() {

        val dummyMovies = PagedTestDataSources.snapshot(DummyData.getMovies())
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(dummyMovies)

        Mockito.`when`(catalogRepository.getAllMovies()).thenReturn(expected)

        movieViewModel.getMovies().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = movieViewModel.getMovies().value

        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)

    }

    @Test
    fun getEmptyMovies() {
        val movies = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movies)

        Mockito.`when`(catalogRepository.getAllMovies()).thenReturn(expected)

        movieViewModel.getMovies().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(expected.value)

        val actualValueDataSize = movieViewModel.getMovies().value?.data?.size
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