package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GeoCodingResponse(
    @SerialName("name") val name: String,
    @SerialName("local_names") val local_names: LocalNames,
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("country") val country: String,
    @SerialName("state") val state: String
)

@Serializable
data class LocalNames(
    @SerialName("et") val et: String,
    @SerialName("ja") val ja: String,
    @SerialName("feature_name") val feature_name: String,
    @SerialName("pl") val pl: String,
    @SerialName("de") val de: String,
    @SerialName("ka") val ka: String,
    @SerialName("zh") val zh: String,
    @SerialName("uk") val uk: String,
    @SerialName("mn") val mn: String,
    @SerialName("os") val os: String,
    @SerialName("la") val la: String,
    @SerialName("lt") val lt: String,
    @SerialName("sk") val sk: String,
    @SerialName("nl") val nl: String,
    @SerialName("en") val en: String,
    @SerialName("be") val be: String,
    @SerialName("it") val it: String,
    @SerialName("az") val az: String,
    @SerialName("ascii") val ascii: String,
    @SerialName("cs") val cs: String,
    @SerialName("ce") val ce: String,
    @SerialName("ko") val ko: String,
    @SerialName("fr") val fr: String,
    @SerialName("ru") val ru: String
)