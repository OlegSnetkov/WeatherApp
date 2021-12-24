package com.avtograv.weatherapp.presentetion.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

internal abstract class VmMainScreen : ViewModel() {
    abstract val stateOutput: LiveData<OptionsViewState>
}