package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.local.entity.LocalWeatherData
import com.example.weatherapp.data.local.entity.LocalWeatherDetails
import com.example.weatherapp.data.network.entity.WeatherData
import com.example.weatherapp.data.network.entity.WeatherDetails
import com.example.weatherapp.data.local.WeatherType
import com.example.weatherapp.utils.kelvinsToCelsius


fun WeatherData.toLocalWeather(): LocalWeatherData {


    return LocalWeatherData(
        cityName = this.name,
        weather = this.weather[0].description,
        weatherType = this.weather[0].toWeatherType(),
        visibility = this.visibility,
        humidity = this.main.humidity.toString() + "%",
        temperature = "${this.main.temp.toInt().kelvinsToCelsius()}°C",
        windSpeed = "${this.wind.speed.toInt()} м/с"
    )

}

fun WeatherData.Weather.toWeatherType(): WeatherType {

    return when (this.main) {

        "Snow" -> {
            WeatherType.Snowy()
        }

        "Thunderstorm" -> {
            if(this.description.contains("rain")) WeatherType.ThunderstormWithRain()
            WeatherType.Thunderstorm()
        }

        "Rain" -> WeatherType.Rainy()

        "Clear" -> WeatherType.Clear()

        "Clouds" -> {
            if(this.description.contains("few")) WeatherType.CloudsWithSun()
            WeatherType.Clouds()
        }


         else -> WeatherType.NotFoundedType()
    }

}


fun WeatherDetails.toLocalWeatherDetails(): LocalWeatherDetails {
    return LocalWeatherDetails(
        this.dt,
        this.weather[0].main,
        this.weather[0].description,
        this.main.humidity,
        this.main.feels_like.toInt(),
        this.wind.speed.toInt()
    )
}