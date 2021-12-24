package com.avtograv.weatherapp.model

data class DataMainScreen(
    val weatherNow: DataNowWeather,
    val weatherToday: DataThreeDaysWeather,
    val weatherTomorrow: DataThreeDaysWeather,
    val weatherAfterTomorrow: DataThreeDaysWeather
)

data class DataNowWeather(
    val id: Int,
    val location: String,
    val tempNow: String,
    val aboutWeatherNow: String
)

data class DataThreeDaysWeather(
    val ic_weather: String,
    val text_weather: String,
    val max_temp: String,
    val min_temp: String
)