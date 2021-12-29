package com.avtograv.weatherapp.data.remote

import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataForecastWeather
import com.avtograv.weatherapp.model.DataWeather


interface RemoteDataSource {

    suspend fun loadingCurrentWeather(location: String): List<DataWeather>

    suspend fun loadingDailyForecast(latLocation: String, lonLocation: String):
            List<DataForecastWeather>

    suspend fun getCoordinates(nameLocation: String): DataCoordinates
}