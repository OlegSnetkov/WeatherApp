package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class MainWeatherResponse(
    @SerialName("temp") val currentTemp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("temp_min") val tempMin: Double,
    @SerialName("temp_max") val tempMax: Double,
    @SerialName("pressure") val pressure: Int,
    @SerialName("humidity") val humidity: Int
)