package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class WeatherResponse(
    @SerialName("lon") val longitude: Double,
    @SerialName("latitude") val feelsLike: Double
)