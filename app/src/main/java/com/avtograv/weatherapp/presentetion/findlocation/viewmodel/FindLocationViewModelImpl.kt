package com.avtograv.weatherapp.presentetion.findlocation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avtograv.weatherapp.common.CommonResult
import com.avtograv.weatherapp.common.Failure
import com.avtograv.weatherapp.common.Success
import com.avtograv.weatherapp.common.exhaustive
import com.avtograv.weatherapp.domain.WeatherRepository
import com.avtograv.weatherapp.model.DataLatLon
import kotlinx.coroutines.launch


internal class FindLocationViewModelImpl(private val repository: WeatherRepository) :
    FindLocationViewModel() {

    override val stateOutput = MutableLiveData<FindLocationViewState>()

    init {
        viewModelScope.launch {
            handleResult(repository.loadLatLon("ulan-ude"))
        }
    }

    private fun handleResult(commonResult: CommonResult<List<DataLatLon>>) {
        when (commonResult) {
            is Success -> handleLocationLoadResult(commonResult.data)
            is Failure -> stateOutput.postValue(FindLocationViewState.FailedLoading(commonResult.exception))
        }.exhaustive
    }

    private fun handleLocationLoadResult(dataLatLon: List<DataLatLon>?) {
        if (dataLatLon != null) {
            stateOutput.postValue(FindLocationViewState.SuccessLoading(dataLatLon))
        } else {
            stateOutput.postValue(FindLocationViewState.NoLocation)
        }
    }
}