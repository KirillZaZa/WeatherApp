package com.example.weatherapp.data.local.entity

import com.example.weatherapp.data.local.WeatherType

data class LocalWeatherHourly(
    val weatherType: WeatherType? = null,
    val time: String? = null,
    val temperature: String? = null
)
