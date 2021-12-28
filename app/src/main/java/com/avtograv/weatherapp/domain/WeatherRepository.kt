package com.avtograv.weatherapp.domain

import com.avtograv.weatherapp.commonModel.CommonResult
import com.avtograv.weatherapp.model.DataWeather

interface WeatherRepository {
    suspend fun loadWeather(findLocation: String): CommonResult<DataWeather>
}