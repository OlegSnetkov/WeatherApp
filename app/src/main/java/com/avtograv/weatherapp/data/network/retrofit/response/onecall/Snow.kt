package com.avtograv.weatherapp.data.network.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snow(

	@SerialName("1h")
	val jsonMember1h: Double? = null
)