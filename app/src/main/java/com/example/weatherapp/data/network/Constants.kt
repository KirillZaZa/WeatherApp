package com.example.weatherapp.data.network

import com.example.weatherapp.R


object BuildConfig{
    private const val API_KEY = R.string.weather_key

    const val API_URL = "pro.openweathermap.org/data/2.5/forecast/hourly?q={cnt=24}&appid={$API_KEY}"
}