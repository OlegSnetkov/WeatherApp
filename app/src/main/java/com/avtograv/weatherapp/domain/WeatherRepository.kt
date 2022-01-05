package com.avtograv.weatherapp.domain

import com.avtograv.weatherapp.common.CommonResult
import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataWeather


interface WeatherRepository {

    suspend fun loadCurrentWeather(locationName: String):
            CommonResult<DataCurrentWeather>

    suspend fun loadWeather(latLocation: String, lonLocation: String):
            CommonResult<List<DataWeather>>

    suspend fun loadLatLon(nameLocation: String):
            CommonResult<List<DataCoordinates>>
}