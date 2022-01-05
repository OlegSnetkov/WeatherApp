package com.avtograv.weatherapp.presentetion.managerlocation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avtograv.weatherapp.common.CommonResult
import com.avtograv.weatherapp.common.Failure
import com.avtograv.weatherapp.common.Success
import com.avtograv.weatherapp.common.exhaustive
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.model.DataCoordinates
import com.avtograv.weatherapp.model.DataCurrentWeather
import kotlinx.coroutines.launch


internal class FindLocationViewModelImpl(private val repository: WeatherRepository) :
    LocationViewModel() {

    override val stateOutputLoadCoordinates = MutableLiveData<LoadingLocationViewState>()
    override val stateOutputLoadWeather = MutableLiveData<LoadingCurrentWeatherViewState>()

    fun loadCoordinates(localName: String) {
        viewModelScope.launch {
            resultLoadingCoordinates(repository.loadLatLon(localName))
        }
    }

    private fun resultLoadingCoordinates(commonResult: CommonResult<List<DataCoordinates>>) {
        when (commonResult) {
            is Success -> successGetCoordinates(commonResult.data)
            is Failure -> stateOutputLoadCoordinates.postValue(
                LoadingLocationViewState.FailedLoading(commonResult.exception)
            )
        }.exhaustive
    }

    private fun successGetCoordinates(dataLatLon: List<DataCoordinates>?) {
        if (dataLatLon != null) {
            stateOutputLoadCoordinates.postValue(LoadingLocationViewState.SuccessLoading(dataLatLon))
        } else {
            stateOutputLoadCoordinates.postValue(LoadingLocationViewState.NoLocation)
        }
    }

    fun loadCurrentWeather(cityName: String) {
        viewModelScope.launch {
            resultLoadingWeather(repository.loadCurrentWeather(cityName))
        }
    }

    private fun resultLoadingWeather(commonResult1: CommonResult<DataCurrentWeather>) {
        when (commonResult1) {
            is Success -> successGetWeather(commonResult1.data)
            is Failure -> stateOutputLoadWeather.postValue(
                LoadingCurrentWeatherViewState.FailedLoading(commonResult1.exception)
            )
        }.exhaustive
    }

    private fun successGetWeather(dataCurrentWeather: DataCurrentWeather?) {
        if (dataCurrentWeather != null) {
            stateOutputLoadWeather.postValue(
                LoadingCurrentWeatherViewState.SuccessLoading(
                    dataCurrentWeather
                )
            )
        } else {
            stateOutputLoadWeather.postValue(LoadingCurrentWeatherViewState.NoLocation)
        }
    }
}