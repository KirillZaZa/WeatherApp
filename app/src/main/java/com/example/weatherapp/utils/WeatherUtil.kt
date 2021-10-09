package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

object WeatherUtil {

    fun getCurrentDate(): String{
        val dateFormat = SimpleDateFormat("d MMMM", Locale.getDefault())

        return "Сегодня, ${dateFormat.format(Date())}"
    }


}

fun Int.kelvinsToCelsius(): Int = this - 273