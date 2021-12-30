package com.avtograv.weatherapp.presentetion.findlocation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


internal abstract class FindLocationViewModel : ViewModel() {
    abstract val stateOutput: LiveData<FindLocationViewState>
}