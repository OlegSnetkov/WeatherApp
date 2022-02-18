package com.avtograv.weatherapp.presentetion.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avtograv.weatherapp.data.RepositoryImpl
import com.avtograv.weatherapp.data.getLocationList
import com.avtograv.weatherapp.data.remote.retrofit.RetrofitDataSource
import com.avtograv.weatherapp.databinding.MainActivityBinding
import com.avtograv.weatherapp.di.NetworkModule
import com.avtograv.weatherapp.di.RepositoryProvider
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.presentetion.main.viewpager.DepthPageTransformer
import com.avtograv.weatherapp.presentetion.main.viewpager.ViewPagerAdapter
import com.avtograv.weatherapp.presentetion.mainfragment.view.MainFragment
import com.avtograv.weatherapp.presentetion.managerlocation.view.LocationManagerFragment
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), MainFragment.ClickListener,
    LocationManagerFragment.BackClickListener, RepositoryProvider {

    private lateinit var binding: MainActivityBinding
    private var itemCountViewPager by Delegates.notNull<Int>()
    private val remoteDataSource = RetrofitDataSource(NetworkModule().api)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemCountViewPager = getLocationList(this).size

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.setPageTransformer(DepthPageTransformer())
        binding.viewpager.adapter = ViewPagerAdapter(
            this, SELECT_MAIN_SCREEN,
            itemCountViewPager
        )
    }

    override fun provideRepository(): WeatherRepository = RepositoryImpl(remoteDataSource)

    override fun letAddLocation() {
        binding.viewpager.adapter = ViewPagerAdapter(
            this,
            SELECT_ADD_LOCATION,
            1
        )
    }

    override fun onBackMainScreen() {
        binding.viewpager.adapter =
            ViewPagerAdapter(
                this,
                SELECT_MAIN_SCREEN,
                itemCountViewPager
            )
    }

    override fun onBackPressed() {
        if (binding.viewpager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.viewpager.currentItem =
                binding.viewpager.currentItem - 1
        }
    }

    companion object {
        private const val SELECT_MAIN_SCREEN = true
        private const val SELECT_ADD_LOCATION = false
    }
}