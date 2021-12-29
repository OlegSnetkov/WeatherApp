package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class CoordinatesResponse(
    @SerialName("lon") val longitude: Double,
    @SerialName("lat") val latitude: Double
)