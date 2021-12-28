package com.avtograv.weatherapp.di

import com.avtograv.weatherapp.domain.WeatherRepository


internal interface RepositoryProvider {
    fun provideRepository(): WeatherRepository
}