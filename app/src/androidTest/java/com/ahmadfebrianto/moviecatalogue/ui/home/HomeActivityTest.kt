package com.ahmadfebrianto.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.source.remote.response.CatalogResponse
import com.ahmadfebrianto.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest {

    private val movies = DummyData.getMovies()
    private val tvShows = DummyData.getTvShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun A_showMainActivity() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movies.size
            )
        )

        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShows.size
            )
        )
    }

    @Test
    fun B_showDetailActivity() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        checkDetailActivity(movies[0])

        onView(withId(R.id.fab_favorite)).perform(click())

        onView(withContentDescription("Navigate up")).perform(click())

        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        checkDetailActivity(tvShows[0])
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withContentDescription("Navigate up")).perform(click())
    }

    @Test
    fun C_showFavoriteActivity() {
        onView(withId(R.id.menu_favorite_page)).perform(click())
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withContentDescription("Navigate up")).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(withContentDescription("Navigate up")).perform(click())
        onView(withContentDescription("Navigate up")).perform(click())
    }

    private fun checkDetailActivity(item: CatalogResponse) {
        onView(withId(R.id.iv_dtl_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_dtl_title_value)).check(matches(withText(item.title)))
        onView(withId(R.id.tv_dtl_rating_value)).check(matches(withText(item.rating)))
        onView(withId(R.id.tv_dtl_release_year_value)).check(matches(withText(item.releaseYear)))
        onView(withId(R.id.tv_dtl_description_value)).check(matches(withText(item.description)))
    }

}