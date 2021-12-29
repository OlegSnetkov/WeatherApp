package com.avtograv.weatherapp.data.remote.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snow(

	@SerialName("1h")
	val jsonMember1h: Double? = null
)