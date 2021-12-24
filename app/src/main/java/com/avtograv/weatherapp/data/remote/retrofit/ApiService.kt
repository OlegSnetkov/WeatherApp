package com.avtograv.weatherapp.data.remote.retrofit

import com.avtograv.weatherapp.data.remote.retrofit.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("weather")
    suspend fun loadCurrentWeather(
        @Query("q") location: String = "",
        @Query("lang") lang: String = "RU",
        @Query("units") units: String = "metric"
    ): CurrentWeatherResponse
}