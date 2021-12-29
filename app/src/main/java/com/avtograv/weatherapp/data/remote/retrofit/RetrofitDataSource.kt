package com.avtograv.weatherapp.data.remote.retrofit

import com.avtograv.weatherapp.data.remote.RemoteDataSource
import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataForecastWeather
import com.avtograv.weatherapp.model.DataWeather


internal class RetrofitDataSource(private val api: ApiService) : RemoteDataSource {

    override suspend fun loadingDailyForecast(): List<DataWeather> {
        val details = api.loadForecastWeather()
        return listOf(
            DataWeather(
                DataCurrentWeather(
                    0,
                    "",
                    details.current.temp?.toInt().toString(),
                    details.current.weather[0].description
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
                    1,
                    "", "", ""
                ),
                DataForecastWeather(
                    "",
                    details.daily[1].weather[0].main,
                    details.daily[1].temp.min.toInt().toString(),
                    details.daily[1].temp.max.toInt().toString()
                ),
                DataForecastWeather(
                    "",
                    details.daily[2].weather[0].main,
                    details.daily[2].temp.min.toInt().toString(),
                    details.daily[3].temp.max.toInt().toString()
                ),
                DataForecastWeather(
                    "Little cloudy",
                    details.daily[3].weather[0].main,
                    details.daily[3].temp.min.toInt().toString(),
                    details.daily[3].temp.max.toInt().toString()
                )
            )
        )
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