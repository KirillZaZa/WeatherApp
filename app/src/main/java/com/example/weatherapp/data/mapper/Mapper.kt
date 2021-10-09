package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.local.entity.LocalWeatherData
import com.example.weatherapp.data.network.entity.WeatherData
import com.example.weatherapp.data.local.WeatherType
import com.example.weatherapp.data.local.entity.LocalWeatherHourly
import com.example.weatherapp.data.network.entity.WeatherHourly
import com.example.weatherapp.utils.convertIntoTime
import com.example.weatherapp.utils.kelvinsToCelsius


fun WeatherData.toLocalWeather(): LocalWeatherData {


    return LocalWeatherData(
        cityName = this.name,
        weather = this.weather[0].description,
        weatherType = this.weather[0].toWeatherType(),
        visibility = this.visibility,
        humidity = this.main.humidity.toString() + "%",
        temperature = "${this.main.temp.toInt().kelvinsToCelsius()}°C",
        windSpeed = "${this.wind.speed.toInt()} м/с",
        lat = "${this.coord.lat}",
        lon = "${this.coord.lon}",
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


fun WeatherHourly.Hourly.Weather.toWeatherType(): WeatherType {

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

fun WeatherHourly.Hourly.toLocalWeatherHourly(): LocalWeatherHourly{
    return LocalWeatherHourly(
        time = this.dt.convertIntoTime(),
        weatherType = this.weather[0].toWeatherType(),
        temperature = "${this.temp.toInt().kelvinsToCelsius()}°C"
    )
}

fun List<WeatherHourly.Hourly>.mapToHourly(): List<LocalWeatherHourly>{
    return this.map {
        it.toLocalWeatherHourly()
    }
}

