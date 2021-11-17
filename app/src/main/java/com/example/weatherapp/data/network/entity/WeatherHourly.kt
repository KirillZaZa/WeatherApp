package com.example.weatherapp.data.network.entity

data class WeatherHourly(
    val current: Current,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
) {
    data class Current(
        val clouds: Int,
        val dew_point: Double,
        val dt: Int,
        val feels_like: Double,
        val humidity: Int,
        val pressure: Int,
        val sunrise: Int,
        val sunset: Int,
        val temp: Double,
        val visibility: Int,
        val weather: List<Weather>,
        val wind_deg: Int,
        val wind_speed: Double
    ) {
        data class Weather(
            val description: String,
            val icon: String,
            val id: Int,
            val main: String
        )
    }

    data class Hourly(
        val clouds: Int,
        val dew_point: Double,
        val dt: Long,
        val feels_like: Double,
        val humidity: Int,
        val pressure: Int,
        val temp: Double,
        val weather: List<Weather>,
        val wind_deg: Int,
        val wind_gust: Double,
        val wind_speed: Double
    ) {
        data class Weather(
            val description: String,
            val icon: String,
            val id: Int,
            val main: String
        )
    }
}