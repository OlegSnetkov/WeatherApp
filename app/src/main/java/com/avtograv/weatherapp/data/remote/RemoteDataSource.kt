package com.avtograv.weatherapp.data.remote

import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataForecastWeather


interface RemoteDataSource {

    suspend fun loadingCurrentWeather(location: String): DataCurrentWeather

    suspend fun loadingDailyForecast(latLocation: String, lonLocation: String):
            List<DataForecastWeather>

    suspend fun getCoordinates(nameLocation: String): DataCoordinates
}