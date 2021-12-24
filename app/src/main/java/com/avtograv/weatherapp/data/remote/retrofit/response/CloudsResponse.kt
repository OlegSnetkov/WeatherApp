package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class CloudsResponse(
    @SerialName("all") val cloudiness: Int
)