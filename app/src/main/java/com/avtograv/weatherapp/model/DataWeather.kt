package com.avtograv.weatherapp.model


data class DataWeather(
    val weatherNow: DataCurrentWeather,
    val weatherToday: DataForecastWeather,
    val weatherTomorrow: DataForecastWeather,
    val weatherAfterTomorrow: DataForecastWeather
)

data class DataCurrentWeather(
    val id: Int,
    val location: String,
    val tempNow: String,
    val aboutWeatherNow: String
)

data class DataForecastWeather(
    val ic_weather: String,
    val text_weather: String,
    val max_temp: String,
    val min_temp: String
)