package com.avtograv.weatherapp.presentetion.mainscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import com.avtograv.weatherapp.Failure
import com.avtograv.weatherapp.Success
import com.avtograv.weatherapp.model.DataMainScreen

internal class VmMainScreenImpl : VmMainScreen() {
    override val stateOutput: MutableLiveData<OptionsViewState> by lazy {
        MutableLiveData<OptionsViewState>().also {
            //           handleResult()
        }
    }

    private fun handleResult(result: Result<DataMainScreen>) {
//        when (result) {
//            is Success -> stateOutput.postValue(OptionsViewState.SuccessLoading(result.data))
//            is Failure -> stateOutput.postValue(OptionsViewState.FailedLoading(result.exception))
//        }
    }
}