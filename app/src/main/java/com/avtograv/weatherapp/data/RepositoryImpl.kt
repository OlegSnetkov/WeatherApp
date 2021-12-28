package com.avtograv.weatherapp.data

import com.avtograv.weatherapp.commonModel.CommonResult
import com.avtograv.weatherapp.commonModel.runCatchingResult
import com.avtograv.weatherapp.data.remote.RemoteDataSource
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.model.DataCurrentWeather


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : WeatherRepository {

    override suspend fun loadWeather(findLocation: String): CommonResult<DataCurrentWeather> {

        return runCatchingResult { remoteDataSource.loadingCurrentWeather(findLocation) }
    }
}