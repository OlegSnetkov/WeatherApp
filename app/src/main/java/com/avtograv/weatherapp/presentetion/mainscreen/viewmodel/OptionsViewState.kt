package com.avtograv.weatherapp.presentetion.mainscreen.viewmodel

import com.avtograv.weatherapp.model.DataCurrentWeather


internal sealed class OptionsViewState {
    data class SuccessLoading(val dataWeather: DataCurrentWeather) : OptionsViewState()
    data class FailedLoading(val exception: Throwable) : OptionsViewState()
}