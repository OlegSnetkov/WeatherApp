package com.avtograv.weatherapp.presentetion.weatherfragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avtograv.weatherapp.common.CommonResult
import com.avtograv.weatherapp.common.Failure
import com.avtograv.weatherapp.common.Success
import com.avtograv.weatherapp.common.exhaustive
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.model.DataWeather
import kotlinx.coroutines.launch


internal class WeatherViewModelImpl(private val repository: WeatherRepository) :
    WeatherViewModel() {

    override val stateOutput = MutableLiveData<WeatherOptionsViewState>()

    fun load(latLocation: String, lonLocation: String) {
        viewModelScope.launch {
            handleResult(repository.loadWeather(latLocation, lonLocation))
        }
    }

    private fun handleResult(commonResult: CommonResult<List<DataWeather>>) {
        when (commonResult) {
            is Success -> stateOutput.postValue(WeatherOptionsViewState.SuccessLoading(commonResult.data))
            is Failure -> stateOutput.postValue(WeatherOptionsViewState.FailedLoading(commonResult.exception))
        }.exhaustive
    }
}