package com.avtograv.weatherapp.presentetion.mainscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avtograv.weatherapp.domain.WeatherRepository


@Suppress("UNCHECKED_CAST")
internal class FactoryViewModel(
    private val repository: WeatherRepository,
    private val nameCity: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        WeatherImplViewModel(repository, nameCity) as T
}