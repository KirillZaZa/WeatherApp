package com.example.weatherapp.data.extension

import com.example.weatherapp.data.local.LocalWeatherDetails
import com.example.weatherapp.data.network.WeatherDetails
import com.example.weatherapp.data.toLocalWeatherDetails

fun List<WeatherDetails>.mapToLocalWeatherList(): List<LocalWeatherDetails>{
    return this.map {
        it.toLocalWeatherDetails()
    }
}