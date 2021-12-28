package com.avtograv.weatherapp.presentetion.mainscreen.viewmodel

import com.avtograv.weatherapp.model.DataWeather


internal sealed class OptionsViewState {
    data class SuccessLoading(val dataWeather: DataWeather) : OptionsViewState()
    data class FailedLoading(val exception: Throwable) : OptionsViewState()
}