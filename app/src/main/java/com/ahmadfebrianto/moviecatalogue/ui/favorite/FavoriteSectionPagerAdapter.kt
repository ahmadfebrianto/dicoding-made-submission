package com.ahmadfebrianto.moviecatalogue.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.ui.favorite.movies.FavoriteMovieFragment
import com.ahmadfebrianto.moviecatalogue.ui.favorite.tvshows.FavoriteTvShowFragment

class FavoriteSectionPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return FavoriteSectionPagerAdapter.TAB_TITLES.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(FavoriteSectionPagerAdapter.TAB_TITLES[position])
    }
}