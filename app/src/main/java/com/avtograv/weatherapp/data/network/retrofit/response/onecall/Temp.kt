package com.avtograv.weatherapp.data.network.retrofit.response.onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Temp(

    @SerialName("min") val min: Double,
    @SerialName("max") val max: Double,
    @SerialName("eve") val eve: Double? = null,
    @SerialName("night") val night: Double? = null,
    @SerialName("day") val day: Double? = null,
    @SerialName("morn") val morn: Double? = null
)