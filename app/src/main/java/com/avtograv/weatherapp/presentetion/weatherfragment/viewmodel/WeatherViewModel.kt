package com.avtograv.weatherapp.presentetion.weatherfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


internal abstract class WeatherViewModel : ViewModel() {
    abstract val stateOutput: LiveData<OptionsViewState>
}