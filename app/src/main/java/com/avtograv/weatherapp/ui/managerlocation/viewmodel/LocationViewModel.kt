package com.avtograv.weatherapp.ui.managerlocation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


internal abstract class LocationViewModel : ViewModel() {
    abstract val stateOutputLoadCoordinates: LiveData<LoadingLocationViewState>
    abstract val stateOutputLoadWeather: LiveData<LoadingCurrentWeatherViewState>
}