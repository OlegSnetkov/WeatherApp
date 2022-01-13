package com.avtograv.weatherapp.presentetion.main

import android.Manifest
import android.content.*
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.avtograv.weatherapp.R
import com.avtograv.weatherapp.data.RepositoryImpl
import com.avtograv.weatherapp.data.locally.SharedPreferenceUtil
import com.avtograv.weatherapp.data.locally.getLocationList
import com.avtograv.weatherapp.data.remote.retrofit.RetrofitDataSource
import com.avtograv.weatherapp.databinding.MainActivityBinding
import com.avtograv.weatherapp.di.NetworkModule
import com.avtograv.weatherapp.di.RepositoryProvider
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.presentetion.main.viewpager.DepthPageTransformer
import com.avtograv.weatherapp.presentetion.main.viewpager.ViewPagerAdapter
import com.avtograv.weatherapp.presentetion.mainfragment.view.MainFragment
import com.avtograv.weatherapp.presentetion.managerlocation.view.LocationManagerFragment
import com.avtograv.weatherapp.service.ForegroundOnlyLocationService
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates


private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

class MainActivity : AppCompatActivity(), MainFragment.ClickListener,
    LocationManagerFragment.BackClickListener, RepositoryProvider,
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var binding: MainActivityBinding
    private var itemCountViewPager by Delegates.notNull<Int>()
    private val remoteDataSource = RetrofitDataSource(NetworkModule().api)

    private var foregroundOnlyLocationServiceBound = false

    // Provides location updates for while-in-use feature.
    private var foregroundOnlyLocationService: ForegroundOnlyLocationService? = null

    // Listens for location broadcasts from ForegroundOnlyLocationService.
    private lateinit var foregroundOnlyBroadcastReceiver: ForegroundOnlyBroadcastReceiver

    private lateinit var sharedPreferences: SharedPreferences

    // Monitors connection to the while-in-use service.
    private val foregroundOnlyServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as ForegroundOnlyLocationService.LocalBinder
            foregroundOnlyLocationService = binder.service
            foregroundOnlyLocationServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            foregroundOnlyLocationService = null
            foregroundOnlyLocationServiceBound = false
        }
    }

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

        foregroundOnlyBroadcastReceiver = ForegroundOnlyBroadcastReceiver()

        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    }

    override fun onStart() {
        super.onStart()
        // TODO
//        sharedPreferences.getBoolean(SharedPreferenceUtil.KEY_FOREGROUND_ENABLED, false)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        val serviceIntent = Intent(this, ForegroundOnlyLocationService::class.java)
        bindService(serviceIntent, foregroundOnlyServiceConnection, Context.BIND_AUTO_CREATE)
    }

    // Review Permissions: Method checks if permissions approved.
    private fun foregroundPermissionApproved(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    // Review Permissions: Method requests permissions.
    private fun requestForegroundPermissions() {
        val provideRationale = foregroundPermissionApproved()

        // If the user denied a previous request, but didn't check "Don't ask again",
        // provide additional rationale.
        if (provideRationale) {
            Snackbar.make(
                binding.root,
                R.string.permission_rationale,
                Snackbar.LENGTH_LONG
            ).setAction(R.string.ok) {
                // Request permission
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
                )
            }
                .show()
        } else {
            Log.d(TAG, "Request foreground only permission")
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    private fun updateLocation() {
        val enabled = sharedPreferences.getBoolean(
            SharedPreferenceUtil.KEY_FOREGROUND_ENABLED, false
        )

        if (enabled) {
            foregroundOnlyLocationService?.unsubscribeToLocationUpdates()
        } else {
            // TODO: Step 1.0, Review Permissions: Checks and requests if needed.
            if (foregroundPermissionApproved()) {
                foregroundOnlyLocationService?.subscribeToLocationUpdates()
                    ?: Log.d(TAG, "Service Not Bound")
            } else {
                requestForegroundPermissions()
            }
        }
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

    // Receiver for location broadcasts from [ForegroundOnlyLocationService]
    private inner class ForegroundOnlyBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val location = intent.getParcelableExtra<Location>(
                ForegroundOnlyLocationService.EXTRA_LOCATION
            )
            // TODO
//            if (location != null) {
//                logResultsToScreen("Foreground location: ${location.toText()}")
//            }
        }
    }

    companion object {
        private const val SELECT_MAIN_SCREEN = true
        private const val SELECT_ADD_LOCATION = false
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        TODO("Not yet implemented")
    }
}