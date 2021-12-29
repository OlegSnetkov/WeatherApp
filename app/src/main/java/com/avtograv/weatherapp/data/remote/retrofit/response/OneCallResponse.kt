package com.avtograv.weatherapp.data.remote.retrofit.response

import com.avtograv.weatherapp.data.remote.retrofit.response.onecall.Current
import com.avtograv.weatherapp.data.remote.retrofit.response.onecall.DailyItem
import com.avtograv.weatherapp.data.remote.retrofit.response.onecall.HourlyItem
import com.avtograv.weatherapp.data.remote.retrofit.response.onecall.MinutelyItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OneCallResponse(

    @SerialName("current") val current: Current,
    @SerialName("timezone") val timezone: String? = null,
    @SerialName("timezone_offset") val timezoneOffset: Int? = null,
    @SerialName("daily") val daily: List<DailyItem>,
    @SerialName("lon") val lon: Double? = null,
    @SerialName("hourly") val hourly: List<HourlyItem?>? = null,
    @SerialName("minutely") val minutely: List<MinutelyItem?>? = null,
    @SerialName("lat") val lat: Double? = null
)