package com.avtograv.weatherapp.data.remote.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyItem(

	@SerialName("temp")
	val temp: Double? = null,

	@SerialName("visibility")
	val visibility: Int? = null,

	@SerialName("uvi")
	val uvi: Int? = null,

	@SerialName("pressure")
	val pressure: Int? = null,

	@SerialName("clouds")
	val clouds: Int? = null,

	@SerialName("feels_like")
	val feelsLike: Double? = null,

	@SerialName("wind_gust")
	val windGust: Double? = null,

	@SerialName("dt")
	val dt: Int? = null,

	@SerialName("pop")
	val pop: Int? = null,

	@SerialName("wind_deg")
	val windDeg: Int? = null,

	@SerialName("dew_point")
	val dewPoint: Double? = null,

	@SerialName("snow")
	val snow: Snow? = null,

	@SerialName("weather")
	val weather: List<WeatherItem?>? = null,

	@SerialName("humidity")
	val humidity: Int? = null,

	@SerialName("wind_speed")
	val windSpeed: Double? = null
)