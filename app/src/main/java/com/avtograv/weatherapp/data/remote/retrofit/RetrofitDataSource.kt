package com.avtograv.weatherapp.data.remote.retrofit

import com.avtograv.weatherapp.data.remote.RemoteDataSource
import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataNowWeather
import com.avtograv.weatherapp.model.ForecastWeather

internal class RetrofitDataSource(private val api: ApiService) : RemoteDataSource {

    override suspend fun loadingCurrentWeather(location: String): DataNowWeather {
        val details = api.loadCurrentWeather(location)
        return DataNowWeather(
            id = details.cityId,
            location = details.cityName,
            tempNow = details.mainWeatherResponse.currentTemp.toString(),
            aboutWeatherNow = details.weatherResponse.weatherCondition
        )
    }

    override suspend fun loadingDailyForecast(
        latLocation: String,
        lonLocation: String
    ): List<ForecastWeather> {
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