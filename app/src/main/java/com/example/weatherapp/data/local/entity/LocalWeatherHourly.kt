package com.example.weatherapp.data.local.entity

import com.example.weatherapp.data.local.WeatherType

data class LocalWeatherHourly(
    val weatherType: WeatherType,
    val time: String,
    val temperature: String
)
