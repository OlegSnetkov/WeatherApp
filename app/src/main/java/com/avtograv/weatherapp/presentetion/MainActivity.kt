package com.avtograv.weatherapp.presentetion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avtograv.weatherapp.data.RepositoryImpl
import com.avtograv.weatherapp.data.remote.retrofit.RetrofitDataSource
import com.avtograv.weatherapp.databinding.MainActivityBinding
import com.avtograv.weatherapp.di.NetworkModule
import com.avtograv.weatherapp.di.RepositoryProvider
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.common.getLocationList
import com.avtograv.weatherapp.presentetion.locationadd.AddLocationFragment
import com.avtograv.weatherapp.presentetion.mainscreen.view.WeatherFragment
import com.avtograv.weatherapp.presentetion.viewpager.DepthPageTransformer
import com.avtograv.weatherapp.presentetion.viewpager.ViewPagerAdapter

class MainActivity : AppCompatActivity(), WeatherFragment.ClickListener,
    AddLocationFragment.BackClickListener, RepositoryProvider {

    private lateinit var binding: MainActivityBinding
    private val remoteDataSource = RetrofitDataSource(NetworkModule().api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.setPageTransformer(DepthPageTransformer())
        binding.viewpager.adapter = ViewPagerAdapter(
            this, SELECT_MAIN_SCREEN,
            getLocationList(this).size
        )
    }

    override fun provideRepository(): WeatherRepository = RepositoryImpl(remoteDataSource)

    override fun letAddLocation() {
        binding.viewpager.adapter = ViewPagerAdapter(this, SELECT_ADD_LOCATION)
    }

    override fun onBackMainScreen() {
        binding.viewpager.adapter =
            ViewPagerAdapter(this, SELECT_MAIN_SCREEN, getLocationList(this).size)
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