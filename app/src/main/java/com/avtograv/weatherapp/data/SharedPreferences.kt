package com.avtograv.weatherapp.data

import android.content.Context
import androidx.preference.PreferenceManager
import com.avtograv.weatherapp.model.DegreesLocation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


const val LOCATION_WEATHER_lIST = "location_weather_list"
const val PREFERENCE_LOCATION = "Ulan-Ude"
const val PREFERENCE_LATITUDE = "51.7885513306"
const val PREFERENCE_LONGITUDE = "107.681968689"


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