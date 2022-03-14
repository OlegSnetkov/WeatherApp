package com.avtograv.weatherapp.data.network.retrofit.response

import com.avtograv.weatherapp.data.network.retrofit.response.current.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentResponse(

    @SerialName("visibility")
	val visibility: Int? = null,

    @SerialName("timezone")
	val timezone: Int? = null,

    @SerialName("main")
	val main: Main,

    @SerialName("clouds")
	val clouds: Clouds? = null,

    @SerialName("sys")
	val sys: Sys? = null,

    @SerialName("dt")
	val dt: Int? = null,

    @SerialName("coord")
	val coord: Coord? = null,

    @SerialName("weather")
	val weather: List<WeatherItem?>? = null,

    @SerialName("name")
	val name: String? = null,

    @SerialName("cod")
	val cod: Int? = null,

    @SerialName("id")
	val id: Int? = null,

    @SerialName("base")
	val base: String? = null,

    @SerialName("wind")
	val wind: Wind? = null
)