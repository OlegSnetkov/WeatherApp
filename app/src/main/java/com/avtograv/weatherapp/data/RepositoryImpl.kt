package com.avtograv.weatherapp.data

import com.avtograv.weatherapp.common.CommonResult
import com.avtograv.weatherapp.common.runCatchingResult
import com.avtograv.weatherapp.data.remote.RemoteDataSource
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataWeather


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : WeatherRepository {

    override suspend fun loadWeather(findLocation: String): CommonResult<List<DataWeather>> {

        return runCatchingResult { remoteDataSource.loadingCurrentWeather(findLocation) }
    }
}