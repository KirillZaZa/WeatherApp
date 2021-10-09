package com.example.weatherapp.data.usecase

import com.example.weatherapp.data.local.entity.LocalWeatherData

interface WeatherUseCase {

    fun getLocalWeatherData(cityName: String, callback: (ResponseUseCase<LocalWeatherData>) -> Unit)

    fun updateWeather(callback: (ResponseUseCase<LocalWeatherData>) -> Unit)

}