package com.avtograv.weatherapp.common

import android.content.Context
import androidx.preference.PreferenceManager
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataForecastWeather
import com.avtograv.weatherapp.model.DataWeather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.random.Random


const val LOCATION_WEATHER_lIST = "location_weather_list"
const val PREFERENCE_LOCATION = "Ulan-Ude"

fun saveLocationList(context: Context, list: List<String>) {
    val json = Gson().toJson(list)
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    sharedPreferences.edit().putString(LOCATION_WEATHER_lIST, json).apply()
}

fun getLocationList(context: Context): List<String> {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val json = sharedPreferences.getString(LOCATION_WEATHER_lIST, "")

    if (json.isNullOrEmpty()) {
        return listOf(PREFERENCE_LOCATION)
    }
    return Gson().fromJson(json, object : TypeToken<List<DataWeather?>?>() {}.type)
}