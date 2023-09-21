package com.example.elevenweek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.elevenweek.data.Documents
import com.example.elevenweek.data.SearchData
import com.example.elevenweek.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val tabtitleArray = arrayOf(
        "검색", "보관함"
    )
    private val tabIconArray = arrayOf(
        R.drawable.search,
        R.drawable.save
    )

    var likeimage: ArrayList<SearchData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

    fun addLikedImage(image : SearchData){
        if(!likeimage.contains(image)){
            likeimage.add(image)
        }
    }

    fun removeLikeImage(image: SearchData){
        likeimage.remove(image)
    }
}