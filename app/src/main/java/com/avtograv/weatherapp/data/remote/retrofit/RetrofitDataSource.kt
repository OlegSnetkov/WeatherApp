package com.avtograv.weatherapp.data.remote.retrofit

import com.avtograv.weatherapp.data.remote.RemoteDataSource
import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataForecastWeather

internal class RetrofitDataSource(private val api: ApiService) : RemoteDataSource {

    override suspend fun loadingCurrentWeather(location: String): DataCurrentWeather {
        val details = api.loadCurrentWeather(location)
        return DataCurrentWeather(
            id = details.cityId,
            location = details.cityName,
            tempNow = details.mainWeatherResponse.currentTemp.toString(),
            aboutWeatherNow = details.weatherResponse.weatherCondition
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