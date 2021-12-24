package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SysResponse(
    @SerialName("type") val internalType: Int,
    @SerialName("id") val internalId: Int,
    @SerialName("country") val country: String,
    @SerialName("sunrise") val sunrise: Int,
    @SerialName("sunset") val sunset: Int
)