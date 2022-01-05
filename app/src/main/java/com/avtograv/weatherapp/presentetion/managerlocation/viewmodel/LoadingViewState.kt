package com.avtograv.weatherapp.presentetion.managerlocation.viewmodel

import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather


internal sealed class LoadingLocationViewState {
    data class SuccessLoading(internal val dataLatLon: List<DataCoordinates>) :
        LoadingLocationViewState()

    data class FailedLoading(val exception: Throwable) : LoadingLocationViewState()
    object NoLocation : LoadingLocationViewState()
}

internal sealed class LoadingCurrentWeatherViewState {
    data class SuccessLoading(internal val dataCurrentWeather: DataCurrentWeather) :
        LoadingCurrentWeatherViewState()

    data class FailedLoading(val exception: Throwable) : LoadingCurrentWeatherViewState()
    object NoLocation : LoadingCurrentWeatherViewState()
}