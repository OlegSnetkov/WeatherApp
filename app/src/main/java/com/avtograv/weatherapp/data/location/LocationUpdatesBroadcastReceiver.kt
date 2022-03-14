package com.avtograv.weatherapp.data.location

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.avtograv.weatherapp.data.location.LocationRepository
import com.avtograv.weatherapp.data.location.db.LocationEntity
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationResult
import java.util.*
import java.util.concurrent.Executors

private const val TAG = "LUBroadcastReceiver"

/**
 * {@link android.app.PendingIntent#getBroadcast(Context, int, Intent, int)} should be used
 * {@link android.app.PendingIntent#getService(Context, int, Intent, int)} should NOT be used.
 */

class LocationUpdatesBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() context:$context, intent:$intent")

        if (intent.action == ACTION_PROCESS_UPDATES) {

            // Checks for location availability changes.
            LocationAvailability.extractLocationAvailability(intent).let { locationAvailability ->
                if (!locationAvailability.isLocationAvailable) {
                    Log.d(TAG, "Location services are no longer available!")
                }
            }

            LocationResult.extractResult(intent).let { locationResult ->
                val locations = locationResult.locations.map { location ->
                    LocationEntity(
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

    // only for debugging purposes
    private fun isAppInForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses ?: return false

        appProcesses.forEach { appProcess ->
            if (appProcess.importance ==
                ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                appProcess.processName == context.packageName
            ) {
                return true
            }
        }
        return false
    }

    companion object {
        const val ACTION_PROCESS_UPDATES = "com.avtograv.weatherapp.action." + "PROCESS_UPDATES"
    }
}