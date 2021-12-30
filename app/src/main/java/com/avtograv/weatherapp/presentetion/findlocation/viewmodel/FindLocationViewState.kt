package com.avtograv.weatherapp.presentetion.findlocation.viewmodel

import com.avtograv.weatherapp.model.DataLatLon


internal sealed class FindLocationViewState {

    data class SuccessLoading(internal val dataLatLon: DataLatLon) :
        FindLocationViewState()

    data class FailedLoading(val exception: Throwable) : FindLocationViewState()

    object NoLocation : FindLocationViewState()
}