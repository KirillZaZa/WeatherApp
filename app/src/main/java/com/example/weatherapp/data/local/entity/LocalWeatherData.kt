package com.example.weatherapp.data.local.entity

import com.example.weatherapp.data.local.WeatherType

data class LocalWeatherData(
    val cityName: String,
    val weather: String,
    val weatherType: WeatherType,
    val visibility: Int,
    val humidity: String,
    val temperature: String,
    val windSpeed: String
)
