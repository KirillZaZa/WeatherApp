package com.example.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.application.appComponent
import com.example.weatherapp.data.local.entity.LocalWeatherData
import com.example.weatherapp.data.local.entity.LocalWeatherDetails
import com.example.weatherapp.databinding.ActivityWeatherBinding
import com.example.weatherapp.presentation.presenters.WeatherPresenterImpl
import com.example.weatherapp.presentation.view.WeatherView
import com.example.weatherapp.presentation.view.status.EventStatus
import com.example.weatherapp.utils.WeatherUtil
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), WeatherView, View.OnClickListener{

    private val viewBinding by viewBinding (ActivityWeatherBinding::bind)

    @Inject
    lateinit var weatherPresenterImpl: WeatherPresenterImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setListeners()

        appComponent.inject(this)


        weatherPresenterImpl.onAttach(context = this, view = this)
        weatherPresenterImpl.updateWeather()
    }


    override fun onClick(view: View?) {
        view ?: return
        when(view){
            viewBinding.buttonUpdate -> {
                weatherPresenterImpl.updateWeather()
                Log.e("WeatherActivity", " clicked ")
            }
        }
    }

    private fun setListeners(){
        viewBinding.buttonUpdate.setOnClickListener(this)
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).apply {
            // customize
        }.show()
    }

    override fun showError(eventStatus: EventStatus) {
        toast(eventStatus.msg)
    }

    override fun showWeatherData(localWeatherData: LocalWeatherData) {
        //TODO show main forecast
        toast(getString(R.string.updated_toast))

        with(viewBinding){
            tvDate.text = WeatherUtil.getCurrentDate()
            tvCity.text = localWeatherData.cityName

            tvCurrentWeather.text = localWeatherData.weather
            tvTemperatureInfo.text = localWeatherData.temperature
            tvHumidityInfo.text = localWeatherData.humidity
            tvWindInfo.text = localWeatherData.windSpeed

            ivWeather.setImageResource(localWeatherData.weatherType.resourceImageId)
        }
    }

    private fun showForecastForDay(localWeatherDataList: List<LocalWeatherDetails>) {
        //TODO show forecast in bottombar (using recyclerview)
    }


    override fun onDestroy() {
        super.onDestroy()
        weatherPresenterImpl.onDetach()
    }



}