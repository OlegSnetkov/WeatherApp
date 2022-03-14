package com.avtograv.weatherapp.ui.mainfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


internal abstract class WeatherViewModel : ViewModel() {
    abstract val stateOutput: LiveData<WeatherOptionsViewState>
}