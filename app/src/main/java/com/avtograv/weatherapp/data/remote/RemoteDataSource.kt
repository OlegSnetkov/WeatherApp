package com.avtograv.weatherapp.data.remote

import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataWeather


interface RemoteDataSource {

    suspend fun loadingDailyForecast(latLocation: String, lonLocation: String):
            List<DataWeather>

    suspend fun getCoordinates(nameLocation: String): DataCoordinates
}