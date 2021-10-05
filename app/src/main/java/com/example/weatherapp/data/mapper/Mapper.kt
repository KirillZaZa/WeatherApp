package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.extension.mapToLocalWeatherList
import com.example.weatherapp.data.local.LocalWeatherData
import com.example.weatherapp.data.local.LocalWeatherDetails
import com.example.weatherapp.data.network.entity.WeatherData
import com.example.weatherapp.data.network.entity.WeatherDetails


fun WeatherData.toLocalWeather(): LocalWeatherData{
    return LocalWeatherData(
        this.city.name,
        this.list.mapToLocalWeatherList()
    )
}


fun WeatherDetails.toLocalWeatherDetails(): LocalWeatherDetails{
    return LocalWeatherDetails(
        this.dt,
        this.weather[0].main,
        this.weather[0].description,
        this.main.humidity,
        this.main.feels_like.toInt(),
        this.wind.speed.toInt()
    )
}