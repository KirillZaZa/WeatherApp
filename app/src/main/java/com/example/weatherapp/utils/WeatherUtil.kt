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

fun Long.convertIntoTime(): String{
    val date = Date(this * 1000)
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    return dateFormat.format(date)
}