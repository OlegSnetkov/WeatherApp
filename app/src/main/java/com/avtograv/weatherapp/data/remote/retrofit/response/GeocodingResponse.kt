package com.avtograv.weatherapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponse(
    @SerialName("GeocodingResponse")
    val geocodingResponse: List<GeocodingResponseItem?>? = null
)

@Serializable
data class LocalNames(
    @SerialName("de")
    val de: String? = null,

    @SerialName("mn")
    val mn: String? = null,

    @SerialName("ce")
    val ce: String? = null,

    @SerialName("ru")
    val ru: String? = null,

    @SerialName("be")
    val be: String? = null,

    @SerialName("os")
    val os: String? = null,

    @SerialName("feature_name")
    val featureName: String? = null,

    @SerialName("ko")
    val ko: String? = null,

    @SerialName("lt")
    val lt: String? = null,

    @SerialName("en")
    val en: String? = null,

    @SerialName("it")
    val it: String? = null,

    @SerialName("fr")
    val fr: String? = null,

    @SerialName("zh")
    val zh: String? = null,

    @SerialName("et")
    val et: String? = null,

    @SerialName("cs")
    val cs: String? = null,

    @SerialName("la")
    val la: String? = null,

    @SerialName("ka")
    val ka: String? = null,

    @SerialName("uk")
    val uk: String? = null,

    @SerialName("ja")
    val ja: String? = null,

    @SerialName("sk")
    val sk: String? = null,

    @SerialName("az")
    val az: String? = null,

    @SerialName("pl")
    val pl: String? = null,

    @SerialName("ascii")
    val ascii: String? = null,

    @SerialName("nl")
    val nl: String? = null
)

@Serializable
data class GeocodingResponseItem(
    @SerialName("local_names")
    val localNames: LocalNames? = null,

    @SerialName("country")
    val country: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("lon")
    val lon: Double? = null,

    @SerialName("state")
    val state: String? = null,

    @SerialName("lat")
    val lat: Double? = null
)
