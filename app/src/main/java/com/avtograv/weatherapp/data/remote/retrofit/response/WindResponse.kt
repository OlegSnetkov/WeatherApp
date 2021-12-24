package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WindResponse(
    @SerialName("speed") val speed: Int,
    @SerialName("deg") val degree: Int
)