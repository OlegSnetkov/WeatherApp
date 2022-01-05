package com.avtograv.weatherapp.presentetion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avtograv.weatherapp.data.RepositoryImpl
import com.avtograv.weatherapp.data.remote.retrofit.RetrofitDataSource
import com.avtograv.weatherapp.databinding.MainActivityBinding
import com.avtograv.weatherapp.di.NetworkModule
import com.avtograv.weatherapp.di.RepositoryProvider
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.data.locally.getLocationList
import com.avtograv.weatherapp.presentetion.managerlocation.view.LocationManagerFragment
import com.avtograv.weatherapp.presentetion.mainfragment.view.MainFragment
import com.avtograv.weatherapp.presentetion.mainfragment.viewpager.DepthPageTransformer
import com.avtograv.weatherapp.presentetion.mainfragment.viewpager.ViewPagerAdapter


class MainActivity : AppCompatActivity(), MainFragment.ClickListener,
    LocationManagerFragment.BackClickListener, RepositoryProvider {

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
        binding.viewpager.adapter = ViewPagerAdapter(
            this,
            SELECT_ADD_LOCATION,
            getLocationList(this).size
        )
    }

    override fun onBackMainScreen() {
        binding.viewpager.adapter =
            ViewPagerAdapter(
                this,
                SELECT_MAIN_SCREEN,
                getLocationList(this).size
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