package com.avtograv.weatherapp.service

import android.app.NotificationManager
import android.app.Service
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.util.TimeUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import java.util.concurrent.TimeUnit

class ForegroundOnlyLocationService : Service() {

    private val configurationChange = false
    private val serviceRunningForeground = false
    private val locationBinder = LocationBinding()
    private lateinit var notificationManager: NotificationManager

    // main class for receiving location updates
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // requirements for the location updates
    // i.e. how often you should receive updates, the priority, etc
    private lateinit var locationRequest: LocationRequest

    // called when FusedLocationProviderClient has a new Location
    private lateinit var locationCallback: LocationCallback

    // used only for local storage of the last known location
    private var currentLocation: Location? = null

    override fun onCreate() {
        Log.d(TAG, "onCreate()")
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create().apply {
            // sets the desired interval for active location updates
            interval = TimeUnit.SECONDS.toMillis(60)
            // sets the fastest rate for active location updates
            fastestInterval = TimeUnit.SECONDS.toMillis(30)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    inner class LocationBinding : Binder() {
        internal val service: ForegroundOnlyLocationService
            get() = this@ForegroundOnlyLocationService
    }
}