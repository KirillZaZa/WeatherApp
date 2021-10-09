package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.entity.WeatherData
import com.example.weatherapp.data.network.entity.WeatherHourly

interface WeatherRepository {

    fun getWeatherFromOpenWeather(cityName: String, callback: (Response<WeatherData>) -> Unit)

    fun updateWeather(callback: (Response<WeatherData>) -> Unit)

    fun getWeatherForecast(callback: (Response<WeatherHourly>) -> Unit)

}