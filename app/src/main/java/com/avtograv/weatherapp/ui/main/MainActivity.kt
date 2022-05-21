package com.avtograv.weatherapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avtograv.weatherapp.data.getLocationList
import com.avtograv.weatherapp.data.network.RepositoryImpl
import com.avtograv.weatherapp.data.network.retrofit.RetrofitDataSource
import com.avtograv.weatherapp.databinding.MainActivityBinding
import com.avtograv.weatherapp.di.NetworkModule
import com.avtograv.weatherapp.di.RepositoryProvider
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.ui.main.viewpager.DepthPageTransformer
import com.avtograv.weatherapp.ui.main.viewpager.ViewPagerAdapter
import com.avtograv.weatherapp.ui.mainfragment.view.MainFragment
import com.avtograv.weatherapp.ui.managerlocation.view.LocationManagerFragment
import com.avtograv.weatherapp.ui.requestpermissions.PermissionRequestFragment


class MainActivity : AppCompatActivity(), MainFragment.CallbacksListener,
    LocationManagerFragment.BackClickListener, PermissionRequestFragment.CallbacksListener,RepositoryProvider {

    private lateinit var binding: MainActivityBinding
    private var itemCountViewPager = 1
    private val remoteDataSource = RetrofitDataSource(NetworkModule().api)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemCountViewPager = getLocationList(this).size

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.setPageTransformer(DepthPageTransformer())
        binding.viewpager.adapter = ViewPagerAdapter(
            this, SELECT_MAIN_SCREEN, 1
        )
    }

    override fun provideRepository(): WeatherRepository = RepositoryImpl(remoteDataSource)

    override fun requestFineLocationPermission() {
        binding.viewpager.adapter = ViewPagerAdapter(
            this,
            SELECT_REQUEST_FINE_LOCATION_PERMISSION
        )
    }

    override fun letAddLocation() {
        binding.viewpager.adapter = ViewPagerAdapter(
            this,
            SELECT_ADD_LOCATION
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
        private const val SELECT_MAIN_SCREEN = 1
        private const val SELECT_ADD_LOCATION = 2
        private const val SELECT_REQUEST_FINE_LOCATION_PERMISSION = 3
    }
}