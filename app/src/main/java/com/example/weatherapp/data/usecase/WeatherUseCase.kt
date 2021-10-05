package com.example.weatherapp.data.usecase

import com.example.weatherapp.data.local.LocalWeatherData

interface WeatherUseCase {

    fun getLocalWeatherData(cityName: String, callback: (UseCaseStatus<LocalWeatherData>) -> Unit)

}