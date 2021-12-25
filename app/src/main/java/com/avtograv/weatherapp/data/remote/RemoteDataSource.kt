package com.avtograv.weatherapp.data.remote

import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataNowWeather
import com.avtograv.weatherapp.model.ForecastWeather


interface RemoteDataSource {

    suspend fun loadingCurrentWeather(location: String): DataNowWeather

    suspend fun loadingDailyForecast(latLocation: String, lonLocation: String):
            List<ForecastWeather>

    suspend fun getCoordinates(nameLocation: String): DataCoordinates
}