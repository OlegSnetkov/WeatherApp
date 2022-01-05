package com.avtograv.weatherapp.data.remote.retrofit.response.current

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherItem(

	@SerialName("icon")
	val icon: String? = null,

	@SerialName("description")
	val description: String? = null,

	@SerialName("main")
	val main: String? = null,

	@SerialName("id")
	val id: Int? = null
)