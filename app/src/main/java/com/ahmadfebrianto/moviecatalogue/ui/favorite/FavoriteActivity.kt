package com.ahmadfebrianto.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteBinding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)

        supportActionBar?.title = getString(R.string.favorite_catalog)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = FavoriteSectionPagerAdapter(this, supportFragmentManager)
        favoriteBinding.favoriteViewPager.adapter = sectionsPagerAdapter
        favoriteBinding.favoriteTabs.setupWithViewPager(favoriteBinding.favoriteViewPager)
        supportActionBar?.elevation = 0f
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}