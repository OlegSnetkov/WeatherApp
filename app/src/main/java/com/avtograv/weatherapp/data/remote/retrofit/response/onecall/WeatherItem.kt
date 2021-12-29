package com.avtograv.weatherapp.data.remote.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherItem(
    @SerialName("icon") val icon: String,
    @SerialName("description") val description: String,
    @SerialName("main") val main: String,
    @SerialName("id") val id: Int
)