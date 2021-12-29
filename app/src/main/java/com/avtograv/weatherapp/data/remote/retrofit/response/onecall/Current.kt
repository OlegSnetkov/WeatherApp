package com.avtograv.weatherapp.data.remote.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class Current(

    @SerialName("sunrise") val sunrise: Int? = null,
    @SerialName("temp") val temp: Double? = null,
    @SerialName("visibility") val visibility: Int? = null,
    @SerialName("uvi") val uvi: Int? = null,
    @SerialName("pressure") val pressure: Int? = null,
    @SerialName("clouds") val clouds: Int? = null,
    @SerialName("feels_like") val feelsLike: Double? = null,
    @SerialName("dt") val dt: Int? = null,
    @SerialName("wind_deg") val windDeg: Int? = null,
    @SerialName("dew_point") val dewPoint: Double? = null,
    @SerialName("sunset") val sunset: Int? = null,
    @SerialName("weather") val weather: List<WeatherItem>,
    @SerialName("humidity") val humidity: Int? = null,
    @SerialName("wind_speed") val windSpeed: Int? = null
)