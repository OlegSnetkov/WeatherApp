package com.avtograv.weatherapp.data.remote.retrofit.response.current

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WindResponse(
    @SerialName("speed") val speed: Int,
    @SerialName("deg") val degree: Int
)