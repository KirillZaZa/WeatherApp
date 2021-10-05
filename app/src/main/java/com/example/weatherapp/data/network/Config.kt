package com.example.weatherapp.data.network

import com.example.weatherapp.R


object ApiConfig{
    private const val API_KEY = R.string.open_weather_api_key

    const val API_URL = "pro.openweathermap.org/data/2.5/forecast/hourly?q={cnt=24}&appid={$API_KEY}"
}