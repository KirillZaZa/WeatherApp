package com.example.weatherapp.data.local


data class LocalWeatherData(
    val cityName: String,
    val list: List<LocalWeatherDetails>,
)