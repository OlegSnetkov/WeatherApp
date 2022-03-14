package com.avtograv.weatherapp.data.network.retrofit

import com.avtograv.weatherapp.data.network.retrofit.response.CurrentResponse
import com.avtograv.weatherapp.data.network.retrofit.response.GeoCodingResponse
import com.avtograv.weatherapp.data.network.retrofit.response.OneCallResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("data/2.5/weather")
    suspend fun loadCurrentWeather(
        @Query("q") cityName: String,
        @Query("lang") language: String = "RU",
        @Query("units") units: String = "metric"
    ): CurrentResponse

    @GET("data/2.5/onecall")
    suspend fun loadForecastWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("exclude") exclude: String = "minutely,hourly,alerts",
        @Query("lang") lang: String = "RU",
        @Query("units") units: String = "metric"
    ): OneCallResponse

    @GET("data/2.5/air_pollution")
    suspend fun loadAirPollution(
        @Query("lat") latitude: String = "51.788898468",
        @Query("lon") longitude: String = "107.682502747",
    )

    @GET("geo/1.0/direct")
    suspend fun loadCoordinatesByLocation(
        @Query("q") location: String = "",
        @Query("lang") lang: String = "RU",
        @Query("limit") numLocation: String = "1"
    ): List<GeoCodingResponse>
}

// http://api.openweathermap.org/data/2.5/weather?q=Ulan-Ude,&lang=Ru&units=metric&appid=aeff0f626d4160211be7d9de79c2cca9

// http://api.openweathermap.org/data/2.5/onecall?lat=51.788898468&lon=107.682502747&lang=Ru&units=metric&exclude=current,minutely,hourly,daily,alerts&appid=aeff0f626d4160211be7d9de79c2cca9

// http://api.openweathermap.org/data/2.5/air_pollution?lat=51.788898468&lon=107.682502747&start=1640412300&end=1640416463&appid=aeff0f626d4160211be7d9de79c2cca9

// http://api.openweathermap.org/geo/1.0/direct?q=ulan-ude&limit=1&lang=Ru&units=metric&appid=aeff0f626d4160211be7d9de79c2cca9