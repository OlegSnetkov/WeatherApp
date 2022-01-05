package com.avtograv.weatherapp.presentetion.mainfragment.viewmodel

import com.avtograv.weatherapp.model.DataWeather


internal sealed class WeatherOptionsViewState {
    data class SuccessLoading(internal val weatherList: List<DataWeather>) :
        WeatherOptionsViewState()

    data class FailedLoading(val exception: Throwable) : WeatherOptionsViewState()
}