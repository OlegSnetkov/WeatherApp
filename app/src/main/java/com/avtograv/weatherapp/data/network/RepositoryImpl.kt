package com.avtograv.weatherapp.data.network

import com.avtograv.weatherapp.common.CommonResult
import com.avtograv.weatherapp.common.runCatchingResult
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataWeather


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : WeatherRepository {

    override suspend fun loadCurrentWeather(locationName: String):
            CommonResult<DataCurrentWeather> {
        return runCatchingResult {
            remoteDataSource.loadingCurrentWeather(locationName)
        }
    }

    override suspend fun loadWeather(
        latLocation: String,
        lonLocation: String
    ): CommonResult<List<DataWeather>> {
        return runCatchingResult {
            remoteDataSource.loadingDailyForecast(latLocation, lonLocation)
        }
    }

    override suspend fun loadLatLon(nameLocation: String): CommonResult<List<DataCoordinates>> {
        return runCatchingResult { remoteDataSource.getCoordinates(nameLocation) }
    }
}