package com.avtograv.weatherapp.domain

import com.avtograv.weatherapp.common.CommonResult
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataWeather

interface WeatherRepository {
    suspend fun loadWeather(latLocation: String, lonLocation: String):
            CommonResult<List<DataWeather>>
}