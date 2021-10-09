package com.example.weatherapp.data.extension

import android.content.Context
import com.example.weatherapp.R


val Context.API_KEY: String
    get() = this.getString(R.string.open_weather_api_key)