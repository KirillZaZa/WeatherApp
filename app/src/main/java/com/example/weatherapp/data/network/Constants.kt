package com.example.weatherapp.data.network


object BuildConfig{
    private const val API_KEY = "775d5fd0a909b842e88b59f8bbf09684"

    const val API_URL = "pro.openweathermap.org/data/2.5/forecast/hourly?q={cnt=24}&appid={$API_KEY}"
}