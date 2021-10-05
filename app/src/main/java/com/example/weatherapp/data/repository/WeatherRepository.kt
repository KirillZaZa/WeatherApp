package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.entity.WeatherData

interface WeatherRepository {

    fun getWeatherFromOpenWeather(cityName: String, callback: (RepositoryStatus<WeatherData>) -> Unit)

}