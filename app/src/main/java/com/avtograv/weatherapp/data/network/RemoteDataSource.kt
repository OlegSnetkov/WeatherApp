package com.avtograv.weatherapp.data.network

import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataWeather


interface RemoteDataSource {

    suspend fun loadingCurrentWeather(cityName: String): DataCurrentWeather

    suspend fun loadingDailyForecast(latLocation: String, lonLocation: String):
            List<DataWeather>

    suspend fun getCoordinates(nameLocation: String): List<DataCoordinates>
}