package com.example.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.application.weatherComponent
import com.example.weatherapp.data.local.LocalWeatherData
import com.example.weatherapp.data.local.LocalWeatherDetails
import com.example.weatherapp.databinding.ActivityWeatherBinding
import com.example.weatherapp.presentation.presenters.WeatherPresenterImpl
import com.example.weatherapp.presentation.view.WeatherView
import com.example.weatherapp.presentation.view.status.EventStatus
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), WeatherView{

    private val viewBinding by viewBinding (ActivityWeatherBinding::bind)

    @Inject
    lateinit var weatherPresenterImpl: WeatherPresenterImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        application.weatherComponent.inject(this)


    }

    override fun showError(eventStatus: EventStatus) {

    }

    override fun showWeatherData(localWeatherData: LocalWeatherData) {

    }

    override fun showForecastForDay(localWeatherDataList: List<LocalWeatherDetails>) {

    }

    override fun toast(msg: String) {

    }

}