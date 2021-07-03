package com.ahmadfebrianto.moviecatalogue.ui_.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ahmadfebrianto.moviecatalogue.core.data.source.Resource
import com.ahmadfebrianto.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.ahmadfebrianto.moviecatalogue.data_.DummyData
import com.ahmadfebrianto.moviecatalogue.detail.DetailMovieViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var detailMovieViewModel: DetailMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: com.ahmadfebrianto.moviecatalogue.core.data.MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        detailMovieViewModel = DetailMovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = DummyData.getMovies()
        val dummyMovie = dummyMovies[0]
        val dummyMovieId = dummyMovie.id

        detailMovieViewModel.setSelectedItem(dummyMovieId)
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(DummyData.getMovieById(dummyMovieId))

        Mockito.`when`(movieRepository.getMovieById(dummyMovieId)).thenReturn(expected)
        detailMovieViewModel.itemMovie.observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = detailMovieViewModel.itemMovie.value
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getTvShow() {
        val dummyTvShows = DummyData.getTvShows()
        val dummyTvShow = dummyTvShows[0]
        val dummyTvShowId = dummyTvShow.id

        detailMovieViewModel.setSelectedItem(dummyTvShowId)
        val expected = MutableLiveData<Resource<TvShowEntity>>()
        expected.value = Resource.success(DummyData.getTvShowById(dummyTvShowId))

        Mockito.`when`(movieRepository.getTvShowById(dummyTvShowId)).thenReturn(expected)
        detailMovieViewModel.itemTvShow.observeForever(tvShowObserver)

        Mockito.verify(tvShowObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = detailMovieViewModel.itemTvShow.value

        assertEquals(expectedValue, actualValue)
    }
}