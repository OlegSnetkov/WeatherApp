package com.avtograv.weatherapp.data.location

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import com.avtograv.weatherapp.data.location.db.MyLocationEntity
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationResult
import java.util.*
import java.util.concurrent.Executors


class LocationUpdatesBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d(TAG, "onReceive() context:$context, intent:$intent")

        if (intent?.action == ACTION_PROCESS_UPDATES) {

            /**
             * Checks for location availability changes.
             */
//            LocationAvailability.extractLocationAvailability(intent).let { locationAvailability ->
//                if (!locationAvailability.isLocationAvailable) {
//                    Log.d(TAG, "Location services are no longer available!")
//                }
//            }

            LocationResult.extractResult(intent).let { locationResult ->
                val locations = locationResult.locations.map { location ->
                    MyLocationEntity(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        foreground = isAppInForeground(context),
                        date = Date(location.time)
                    )
                }
                if (locations.isNotEmpty()) {
                    LocationRepository.getInstance(context, Executors.newSingleThreadExecutor())
                        .addLocations(locations)
                }
            }
        }
    }

    private fun isAppInForeground(context: Context): Boolean {
        return false
    }

    companion object {
        const val ACTION_PROCESS_UPDATES =
            "com.avtograv.weatherapp.data.location.action." + "PROCESS_UPDATES"
    }
}