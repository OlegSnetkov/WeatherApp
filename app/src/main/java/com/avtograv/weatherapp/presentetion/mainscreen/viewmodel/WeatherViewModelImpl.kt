package com.avtograv.weatherapp.presentetion.mainscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avtograv.weatherapp.common.CommonResult
import com.avtograv.weatherapp.common.Failure
import com.avtograv.weatherapp.common.Success
import com.avtograv.weatherapp.common.exhaustive
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.model.DataWeather
import kotlinx.coroutines.launch


//internal class WeatherViewModelImpl(repository: WeatherRepository, nameCity: String) :
internal class WeatherViewModelImpl(repository: WeatherRepository) :
    WeatherViewModel() {

    override val stateOutput = MutableLiveData<OptionsViewState>()

    init {
        viewModelScope.launch {
//            handleResult(repository.loadWeather(nameCity))
            handleResult(repository.loadWeather())
        }
    }

    private fun handleResult(commonResult: CommonResult<List<DataWeather>>) {
        when (commonResult) {
            is Success -> stateOutput.postValue(OptionsViewState.SuccessLoading(commonResult.data))
            is Failure -> stateOutput.postValue(OptionsViewState.FailedLoading(commonResult.exception))
        }.exhaustive
    }
}