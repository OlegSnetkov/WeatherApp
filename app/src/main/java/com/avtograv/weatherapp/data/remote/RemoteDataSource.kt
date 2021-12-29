package com.avtograv.weatherapp.data.remote

import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataWeather


interface RemoteDataSource {

//    suspend fun loadingCurrentWeather(location: String): List<DataWeather>

//    suspend fun loadingDailyForecast(latLocation: String, lonLocation: String):
//            List<DataForecastWeather>

    suspend fun loadingDailyForecast():
            List<DataWeather>

    suspend fun getCoordinates(nameLocation: String): DataCoordinates
}