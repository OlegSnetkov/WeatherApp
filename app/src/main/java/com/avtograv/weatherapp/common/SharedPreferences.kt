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

fun saveLocationList(context: Context, list: List<DataWeather>) {
    val json = Gson().toJson(list)
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    sharedPreferences.edit().putString(LOCATION_WEATHER_lIST, json).apply()
}

fun getLocationList(context: Context): List<DataWeather> {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val json = sharedPreferences.getString(LOCATION_WEATHER_lIST, "")

    if (json.isNullOrEmpty()) {
        return listOf(
            DataWeather(
                DataCurrentWeather(
                    0,
                    PREFERENCE_LOCATION,
                    tempNow = Random.nextInt(-30, 30).toString(),
                    aboutWeatherNow = "Clear"
                ),
                DataForecastWeather(
                    "", "", "", ""
                ),
                DataForecastWeather(
                    "", "", "", ""
                ),
                DataForecastWeather(
                    "", "", "", ""
                )
            ),

            DataWeather(
                DataCurrentWeather(
                    2,
                    "", "", ""
                ),
                DataForecastWeather(
                    "",
                    "Cloudy",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                ),
                DataForecastWeather(
                    "",
                    "Winter",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                ),
                DataForecastWeather(
                    "Little cloudy",
                    "",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                )
            )
        )
    }
    return Gson().fromJson(json, object : TypeToken<List<DataWeather?>?>() {}.type)
}