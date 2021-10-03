package com.example.weatherapp.data

import com.example.weatherapp.data.local.LocalWeatherData
import com.example.weatherapp.data.local.LocalWeatherDetails
import com.example.weatherapp.data.network.WeatherData
import com.example.weatherapp.data.network.WeatherDetails


fun WeatherData.toLocalWeather(): LocalWeatherData{
    return LocalWeatherData(
        this.city.name,
        this.list
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