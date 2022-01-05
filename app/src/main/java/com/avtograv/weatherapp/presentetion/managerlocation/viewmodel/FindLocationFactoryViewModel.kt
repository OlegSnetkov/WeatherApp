package com.avtograv.weatherapp.presentetion.managerlocation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avtograv.weatherapp.domain.WeatherRepository


@Suppress("UNCHECKED_CAST")
internal class FindLocationFactoryViewModel(
    private val repository: WeatherRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        FindLocationViewModelImpl(repository) as T
}