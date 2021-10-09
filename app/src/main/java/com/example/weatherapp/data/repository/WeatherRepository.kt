package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.entity.WeatherData

interface WeatherRepository {

    fun getWeatherFromOpenWeather(cityName: String, callback: (Response<WeatherData>) -> Unit)

    fun updateWeather(callback: (Response<WeatherData>) -> Unit)
}