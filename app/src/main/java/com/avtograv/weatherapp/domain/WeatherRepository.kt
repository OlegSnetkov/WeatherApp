package com.avtograv.weatherapp.domain

import com.avtograv.weatherapp.model.DataMainScreen

interface WeatherRepository {
    suspend fun loadWeather(findLocation: String): Result<DataMainScreen>
}