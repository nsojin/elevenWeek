package com.example.elevenweek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.elevenweek.data.Documents
import com.example.elevenweek.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var searchImage = mutableListOf<Documents>()

    private val tabtitleArray = arrayOf(
        "검색", "보관함"
    )
    private val tabIconArray = arrayOf(
        R.drawable.search,
        R.drawable.save
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)


        var viewPager = binding.viewpager
        var tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = tabtitleArray[position]
            tab.icon = getDrawable(tabIconArray[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return true
    }
}