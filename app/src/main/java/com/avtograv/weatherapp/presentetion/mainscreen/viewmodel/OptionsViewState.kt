package com.avtograv.weatherapp.presentetion.mainscreen.viewmodel

import com.avtograv.weatherapp.model.DataMainScreen

internal sealed class OptionsViewState {
    data class SuccessLoading(val dataMainScreen: DataMainScreen) : OptionsViewState()
    data class FailedLoading(val exception: Throwable) : OptionsViewState()
}