package com.ahmadfebrianto.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = HomeSectionsPagerAdapter(this, supportFragmentManager)
        binding.homeViewPager.adapter = sectionsPagerAdapter
        binding.homeTabs.setupWithViewPager(binding.homeViewPager)
        supportActionBar?.elevation = 0f


    }
}