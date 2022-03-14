package com.avtograv.weatherapp.data.network.retrofit.response.current

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Clouds(

	@SerialName("all")
	val all: Int? = null
)