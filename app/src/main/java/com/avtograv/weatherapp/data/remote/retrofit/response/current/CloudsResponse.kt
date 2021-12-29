package com.avtograv.weatherapp.data.remote.retrofit.response.current

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class CloudsResponse(
    @SerialName("all") val cloudiness: Int
)