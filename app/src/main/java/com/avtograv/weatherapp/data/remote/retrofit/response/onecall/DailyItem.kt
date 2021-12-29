package com.avtograv.weatherapp.data.remote.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyItem(
    @SerialName("moonset") val moonset: Int? = null,
    @SerialName("sunrise") val sunrise: Int? = null,
    @SerialName("temp") val temp: Temp,
    @SerialName("moon_phase") val moonPhase: Double? = null,
    @SerialName("uvi") val uvi: Double? = null,
    @SerialName("moonrise") val moonrise: Int? = null,
    @SerialName("pressure") val pressure: Int? = null,
    @SerialName("clouds") val clouds: Int? = null,
    @SerialName("feels_like") val feelsLike: FeelsLike? = null,
    @SerialName("wind_gust") val windGust: Double? = null,
    @SerialName("dt") val dt: Int? = null,
    @SerialName("pop") val pop: Double? = null,
    @SerialName("wind_deg") val windDeg: Int? = null,
    @SerialName("dew_point") val dewPoint: Double? = null,
    @SerialName("snow") val snow: Double? = null,
    @SerialName("sunset") val sunset: Int? = null,
    @SerialName("weather") val weather: List<WeatherItem>,
    @SerialName("humidity") val humidity: Int? = null,
    @SerialName("wind_speed") val windSpeed: Double? = null
)