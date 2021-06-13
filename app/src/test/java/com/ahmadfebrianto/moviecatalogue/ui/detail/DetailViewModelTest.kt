package com.ahmadfebrianto.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahmadfebrianto.moviecatalogue.data.CatalogRepository
import com.ahmadfebrianto.moviecatalogue.data.DummyData
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ahmadfebrianto.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = DummyData.getMovies()
        val dummyMovie = dummyMovies[0]
        val dummyMovieId = dummyMovie.id

        detailViewModel.setSelectedItem(dummyMovieId)
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(DummyData.getMovieById(dummyMovieId))

        Mockito.`when`(catalogRepository.getMovieById(dummyMovieId)).thenReturn(expected)
        detailViewModel.itemMovie.observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = detailViewModel.itemMovie.value
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getTvShow() {
        val dummyTvShows = DummyData.getTvShows()
        val dummyTvShow = dummyTvShows[0]
        val dummyTvShowId = dummyTvShow.id

        detailViewModel.setSelectedItem(dummyTvShowId)
        val expected = MutableLiveData<Resource<TvShowEntity>>()
        expected.value = Resource.success(DummyData.getTvShowById(dummyTvShowId))

        Mockito.`when`(catalogRepository.getTvShowById(dummyTvShowId)).thenReturn(expected)
        detailViewModel.itemTvShow.observeForever(tvShowObserver)

        Mockito.verify(tvShowObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = detailViewModel.itemTvShow.value

        assertEquals(expectedValue, actualValue)
    }
}