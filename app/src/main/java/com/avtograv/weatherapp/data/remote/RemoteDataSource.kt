package com.avtograv.weatherapp.data.remote

import com.avtograv.weatherapp.model.DataNowWeather


interface RemoteDataSource {
    suspend fun loadingCurrentWeather(loadLocation: String): DataNowWeather
}