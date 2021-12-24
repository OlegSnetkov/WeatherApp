package com.avtograv.weatherapp

import android.content.Context
import androidx.preference.PreferenceManager
import com.avtograv.weatherapp.model.DataMainScreen
import com.avtograv.weatherapp.model.DataNowWeather
import com.avtograv.weatherapp.model.DataThreeDaysWeather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlin.random.Random


const val LOCATION_WEATHER_lIST = "location_weather_list"
const val PREFERENCE_LOCATION = "Ulan-Ude"

fun saveLocationList(context: Context, list: List<DataMainScreen>) {
    val json = Gson().toJson(list)
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    sharedPreferences.edit().putString(LOCATION_WEATHER_lIST, json).apply()
}

fun getLocationList(context: Context): List<DataMainScreen> {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val json = sharedPreferences.getString(LOCATION_WEATHER_lIST, "")

    if (json.isNullOrEmpty()) {
        return listOf(
            DataMainScreen(
                DataNowWeather(
                    0,
                    PREFERENCE_LOCATION,
                    tempNow = Random.nextInt(-30, 30).toString(),
                    aboutWeatherNow = "Clear"
                ),
                DataThreeDaysWeather(
                    "",
                    "",
                    "",
                    ""
                ),
                DataThreeDaysWeather(
                    "",
                    "",
                    "",
                    ""
                ),
                DataThreeDaysWeather(
                    "",
                    "",
                    "",
                    ""
                )
            ),

            DataMainScreen(
                DataNowWeather(
                    2,
                    "",
                    "",
                    ""
                ),
                DataThreeDaysWeather(
                    "",
                    "Cloudy",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                ),
                DataThreeDaysWeather(
                    "",
                    "Winter",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                ),
                DataThreeDaysWeather(
                    "Little cloudy",
                    "",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                )
            )
        )
    }
    return Gson().fromJson(json, object : TypeToken<List<DataMainScreen?>?>() {}.type)
}