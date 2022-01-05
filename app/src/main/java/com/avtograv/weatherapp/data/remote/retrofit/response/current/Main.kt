package com.avtograv.weatherapp.data.remote.retrofit.response.current

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(

	@SerialName("temp")
	val temp: Double? = null,

	@SerialName("temp_min")
	val tempMin: Double? = null,

	@SerialName("humidity")
	val humidity: Int? = null,

	@SerialName("pressure")
	val pressure: Int? = null,

	@SerialName("feels_like")
	val feelsLike: Double? = null,

	@SerialName("temp_max")
	val tempMax: Double? = null
)