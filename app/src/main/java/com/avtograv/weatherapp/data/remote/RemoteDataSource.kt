package com.avtograv.weatherapp.data.remote

import com.avtograv.weatherapp.model.DataNowWeather
import com.avtograv.weatherapp.model.ForecastWeather


interface RemoteDataSource {

    suspend fun loadingCurrentWeather(loadLocation: String): DataNowWeather
    suspend fun loadingDailyForecast (loadLocation: String): List<ForecastWeather>

}