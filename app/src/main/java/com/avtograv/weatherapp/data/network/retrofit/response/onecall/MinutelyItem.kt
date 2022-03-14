package com.avtograv.weatherapp.data.network.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinutelyItem(

	@SerialName("dt")
	val dt: Int? = null,

	@SerialName("precipitation")
	val precipitation: Int? = null
)