package com.example.weatherapp.data.local

import com.example.weatherapp.R

sealed class WeatherType{

    abstract val resourceImageId: Int

    data class Snowy(
        override val resourceImageId: Int = R.drawable.ic_snowy_sun
    ): WeatherType()

    data class SnowyWithSun(
        override val resourceImageId: Int = R.drawable.ic_snowy_sun
    ): WeatherType()

    data class Rainy(
        override val resourceImageId: Int = R.drawable.ic_rain
    ): WeatherType()

    data class Clear(
        override val resourceImageId: Int = R.drawable.ic_sun
    ): WeatherType()

    data class Clouds(
        override val resourceImageId: Int = R.drawable.ic_cloudy
    ): WeatherType()

    data class CloudsWithSun(
        override val resourceImageId: Int = R.drawable.ic_cloudy_sun
    ): WeatherType()

    data class Thunderstorm(
        override val resourceImageId: Int = R.drawable.ic_storm
    ): WeatherType()

    data class ThunderstormWithRain(
        override val resourceImageId: Int = R.drawable.ic_storm_with_rain
    ): WeatherType()

    data class NotFoundedType(
        override val resourceImageId: Int = R.drawable.ic_sad
    ): WeatherType()



}
