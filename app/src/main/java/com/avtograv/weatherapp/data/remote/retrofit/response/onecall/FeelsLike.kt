package com.avtograv.weatherapp.data.remote.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeelsLike(

	@SerialName("eve")
	val eve: Double? = null,

	@SerialName("night")
	val night: Double? = null,

	@SerialName("day")
	val day: Double? = null,

	@SerialName("morn")
	val morn: Double? = null
)