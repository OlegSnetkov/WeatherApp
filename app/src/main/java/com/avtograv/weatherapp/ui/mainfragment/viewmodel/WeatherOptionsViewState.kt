package com.avtograv.weatherapp.ui.mainfragment.viewmodel

import com.avtograv.weatherapp.model.DataWeather


internal sealed class WeatherOptionsViewState {

    data class SuccessLoading(internal val weatherList: List<DataWeather>) :
        WeatherOptionsViewState()

    data class FailedLoading(val exception: Throwable) : WeatherOptionsViewState()
}