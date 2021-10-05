package com.example.weatherapp.data.extension

import com.example.weatherapp.data.local.LocalWeatherDetails
import com.example.weatherapp.data.network.entity.WeatherDetails
import com.example.weatherapp.data.mapper.toLocalWeatherDetails

fun List<WeatherDetails>.mapToLocalWeatherList(): List<LocalWeatherDetails>{
    return this.map {
        it.toLocalWeatherDetails()
    }
}