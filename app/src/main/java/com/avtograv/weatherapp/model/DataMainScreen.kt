package com.avtograv.weatherapp.model

data class DataMainScreen(
    val weatherNow: DataNowWeather,
    val weatherToday: ForecastWeather,
    val weatherTomorrow: ForecastWeather,
    val weatherAfterTomorrow: ForecastWeather
)

data class DataNowWeather(
    val id: Int,
    val location: String,
    val tempNow: String,
    val aboutWeatherNow: String
)

data class ForecastWeather(
    val ic_weather: String,
    val text_weather: String,
    val max_temp: String,
    val min_temp: String
)