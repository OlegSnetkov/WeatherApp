package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GeoCodingResponse(
    @SerialName("name") val name: String,
    @SerialName("local_names") val local_names: LocalNames? = null,
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("country") val country: String,
    @SerialName("state") val state: String? = null
)

@Serializable
data class LocalNames(
    @SerialName("et") val et: String? = null,
    @SerialName("ja") val ja: String? = null,
    @SerialName("feature_name") val feature_name: String? = null,
    @SerialName("pl") val pl: String? = null,
    @SerialName("de") val de: String? = null,
    @SerialName("ka") val ka: String? = null,
    @SerialName("zh") val zh: String? = null,
    @SerialName("uk") val uk: String? = null,
    @SerialName("mn") val mn: String? = null,
    @SerialName("os") val os: String? = null,
    @SerialName("la") val la: String? = null,
    @SerialName("lt") val lt: String? = null,
    @SerialName("sk") val sk: String? = null,
    @SerialName("nl") val nl: String? = null,
    @SerialName("en") val en: String? = null,
    @SerialName("be") val be: String? = null,
    @SerialName("it") val it: String? = null,
    @SerialName("az") val az: String? = null,
    @SerialName("ascii") val ascii: String? = null,
    @SerialName("cs") val cs: String? = null,
    @SerialName("ce") val ce: String? = null,
    @SerialName("ko") val ko: String? = null,
    @SerialName("fr") val fr: String? = null,
    @SerialName("ru") val ru: String? = null
)