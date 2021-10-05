package com.example.weatherapp.data.local

import com.example.weatherapp.data.network.entity.WeatherDetails

data class LocalWeatherData(
    val cityName: String,
    val list: List<WeatherDetails>,
)