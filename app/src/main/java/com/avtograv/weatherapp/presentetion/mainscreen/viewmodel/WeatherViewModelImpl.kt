package com.avtograv.weatherapp.presentetion.mainscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avtograv.weatherapp.commonModel.CommonResult
import com.avtograv.weatherapp.commonModel.Failure
import com.avtograv.weatherapp.commonModel.Success
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.exhaustive
import com.avtograv.weatherapp.model.DataCurrentWeather
import kotlinx.coroutines.launch


internal class WeatherViewModelImpl(repository: WeatherRepository, nameCity: String) :
    WeatherViewModel() {

    override val stateOutput = MutableLiveData<OptionsViewState>()

    init {
        viewModelScope.launch {
            handleResult(repository.loadWeather(nameCity))
        }
    }

    private fun handleResult(result: CommonResult<DataCurrentWeather>) {
        when (result) {
            is Success -> stateOutput.postValue(OptionsViewState.SuccessLoading(result.data))
            is Failure -> stateOutput.postValue(OptionsViewState.FailedLoading(result.exception))
        }.exhaustive
    }
}