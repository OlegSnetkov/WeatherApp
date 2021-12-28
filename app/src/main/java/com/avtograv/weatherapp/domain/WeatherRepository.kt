package com.avtograv.weatherapp.domain

import com.avtograv.weatherapp.commonModel.CommonResult
import com.avtograv.weatherapp.model.DataCurrentWeather

interface WeatherRepository {
    suspend fun loadWeather(findLocation: String): CommonResult<DataCurrentWeather>
}