package com.example.weatherapp.data.network.entity

data class WeatherData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherDetails>,
    val message: Double
) {
    data class City(
        val coord: Coord,
        val country: String,
        val id: Int,
        val name: String,
        val sunrise: Int,
        val sunset: Int,
        val timezone: Int
    ) {
        data class Coord(
            val lat: Double,
            val lon: Double
        )
    }
}