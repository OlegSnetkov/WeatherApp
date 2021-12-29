package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class CommonWeatherResponse(
    @SerialName("coord") val coordinatesResponse: CoordinatesResponse,
    @SerialName("weather") val weatherResponse: List<WeatherResponse>,
    @SerialName("base") val baseResponse: String,
    @SerialName("main") val mainWeatherResponse: MainWeatherResponse,
    @SerialName("visibility") val visibilityResponse: Int,
    @SerialName("wind") val windResponse: WindResponse,
    @SerialName("clouds") val cloudsResponse: CloudsResponse,
    @SerialName("dt") val time: Int,
    @SerialName("sys") val sys: SysResponse,
    @SerialName("timezone") val timezone: Int,
    @SerialName("id") val cityId: Int,
    @SerialName("name") val cityName: String,
    @SerialName("cod") val internalCod: Int
)