package com.avtograv.weatherapp.data.remote.retrofit

import com.avtograv.weatherapp.common.PREFERENCE_LOCATION
import com.avtograv.weatherapp.data.remote.RemoteDataSource
import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataForecastWeather
import com.avtograv.weatherapp.model.DataWeather
import kotlin.random.Random

internal class RetrofitDataSource(private val api: ApiService) : RemoteDataSource {

    override suspend fun loadingCurrentWeather(location: String): List<DataWeather> {
        val details = api.loadCurrentWeather(location)
        return listOf(
            DataWeather(
                DataCurrentWeather(
                    0,
                    PREFERENCE_LOCATION,
                    tempNow = Random.nextInt(-30, 30).toString(),
                    aboutWeatherNow = "Clear"
                ),
                DataForecastWeather(
                    "", "", "", ""
                ),
                DataForecastWeather(
                    "", "", "", ""
                ),
                DataForecastWeather(
                    "", "", "", ""
                )
            ),

            DataWeather(
                DataCurrentWeather(
                    2,
                    "", "", ""
                ),
                DataForecastWeather(
                    "",
                    "Cloudy",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                ),
                DataForecastWeather(
                    "",
                    "Winter",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                ),
                DataForecastWeather(
                    "Little cloudy",
                    "",
                    max_temp = Random.nextInt(-30, 30).toString(),
                    min_temp = Random.nextInt(-30, 30).toString()
                )
            )
        )
    }

    override suspend fun loadingDailyForecast(
        latLocation: String,
        lonLocation: String
    ): List<DataForecastWeather> {
        TODO("Not yet implemented")
    }

    //TODO
    override suspend fun getCoordinates(nameLocation: String): DataCoordinates {
        val details = api.loadCoordinatesByLocation(nameLocation)
        return DataCoordinates(
            locationName = "",
            latLocation = "",
            lonLocation = ""
        )
    }
}