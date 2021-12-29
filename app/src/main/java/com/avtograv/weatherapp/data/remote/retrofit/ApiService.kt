package com.avtograv.weatherapp.data.remote.retrofit

import com.avtograv.weatherapp.data.remote.retrofit.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("data/2.5/weather")
    suspend fun loadCurrentWeather(
        @Query("q") location: String = "",
        @Query("lang") lang: String = "RU",
        @Query("units") units: String = "metric"
    ): CurrentWeatherResponse

    @GET("data/2.5/onecall")
    suspend fun loadForecastWeather(
        @Query("lat") latitude: String = "51.788898468",
        @Query("lon") longitude: String = "107.682502747",
        @Query("exclude") exclude: String = "current,minutely,hourly,alerts",
        @Query("lang") lang: String = "RU",
        @Query("units") units: String = "metric"
    )

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
    )


}

// http://api.openweathermap.org/data/2.5/weather?q=Ulan-Ude,&lang=Ru&units=metric&appid=aeff0f626d4160211be7d9de79c2cca9

// http://api.openweathermap.org/data/2.5/onecall?lat=51.788898468&lon=107.682502747&lang=Ru&units=metric&exclude=current,minutely,hourly,daily,alerts&appid=aeff0f626d4160211be7d9de79c2cca9

// http://api.openweathermap.org/data/2.5/air_pollution?lat=51.788898468&lon=107.682502747&start=1640412300&end=1640416463&appid=aeff0f626d4160211be7d9de79c2cca9

// http://api.openweathermap.org/geo/1.0/direct?q=ulan-ude&limit=1&lang=Ru&units=metric&appid=aeff0f626d4160211be7d9de79c2cca9