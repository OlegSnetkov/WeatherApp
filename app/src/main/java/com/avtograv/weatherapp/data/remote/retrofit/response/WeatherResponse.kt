package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class WeatherResponse(
    @SerialName("id") val WeatherConditionId: Int,
    @SerialName("main") val weatherParameters: String,          // Clear, Rain, Snow, Extreme etc
    @SerialName("description") val weatherCondition: String,    // "пасмурно" - can get the output in other language
    @SerialName("icon") val iconId: String
)