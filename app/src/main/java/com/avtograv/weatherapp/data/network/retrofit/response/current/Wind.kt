package com.avtograv.weatherapp.data.network.retrofit.response.current

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(

	@SerialName("deg")
	val deg: Int? = null,

	@SerialName("speed")
	val speed: Double? = null
)