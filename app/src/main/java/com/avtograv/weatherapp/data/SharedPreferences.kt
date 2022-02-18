package com.avtograv.weatherapp.data

import android.content.Context
import android.location.Location
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.avtograv.weatherapp.R
import com.avtograv.weatherapp.model.DegreesLocation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


const val LOCATION_WEATHER_lIST = "location_weather_list"
const val PREFERENCE_LOCATION = "Ulan-Ude"
const val PREFERENCE_LATITUDE = "51.7885513306"
const val PREFERENCE_LONGITUDE = "107.681968689"

fun Location?.toText(): String {
    return if (this != null) {
        "($latitude, $longitude)"
    } else {
        "Unknown location"
    }
}

internal object SharedPreferenceUtil {

    const val KEY_FOREGROUND_ENABLED = "tracking_foreground_location"

    // Returns true if requesting location updates, otherwise returns false.
    fun getLocationTrackingPref(context: Context): Boolean =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
            .getBoolean(KEY_FOREGROUND_ENABLED, false)


    // Stores the location updates state in SharedPreferences
// @param requestingLocationUpdates The location updates state.
    fun saveLocationTrackingPref(context: Context, requestingLocationUpdates: Boolean) =
        context.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        ).edit {
            putBoolean(KEY_FOREGROUND_ENABLED, requestingLocationUpdates)
        }
}

fun saveLocationList(context: Context, list: List<DegreesLocation>) {
    val json = Gson().toJson(list)
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    sharedPreferences.edit().putString(LOCATION_WEATHER_lIST, json).apply()
}

fun getLocationList(context: Context): List<DegreesLocation> {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val json = sharedPreferences.getString(LOCATION_WEATHER_lIST, "")

    if (json.isNullOrEmpty()) {
        return listOf(
            DegreesLocation(
                0, PREFERENCE_LOCATION, PREFERENCE_LATITUDE, PREFERENCE_LONGITUDE
            )
        )
    }
    return Gson().fromJson(json, object : TypeToken<List<DegreesLocation?>?>() {}.type)
}