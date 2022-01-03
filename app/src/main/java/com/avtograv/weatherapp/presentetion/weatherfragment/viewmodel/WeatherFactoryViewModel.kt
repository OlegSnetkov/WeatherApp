package com.avtograv.weatherapp.presentetion.weatherfragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avtograv.weatherapp.domain.WeatherRepository


@Suppress("UNCHECKED_CAST")
internal class WeatherFactoryViewModel(
    private val repository: WeatherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        WeatherViewModelImpl(repository) as T
}