package com.example.weatherapp.presentation.presenters


import android.util.Log
import com.example.weatherapp.R
import com.example.weatherapp.data.usecase.ResponseUseCase
import com.example.weatherapp.data.usecase.WeatherUseCaseImpl
import com.example.weatherapp.presentation.presenters.base.BasePresenter
import com.example.weatherapp.presentation.view.WeatherView
import com.example.weatherapp.presentation.view.status.EventStatus
import javax.inject.Inject


interface WeatherPresenter {


    fun searchButtonClick(cityName: String?)

    fun updateWeather()

}

class WeatherPresenterImpl @Inject constructor(

    private val weatherUseCase: WeatherUseCaseImpl
) : BasePresenter<WeatherView>(), WeatherPresenter {





    override fun searchButtonClick(cityName: String?) {

        _context ?: return

        if (cityName == null) {
            _view!!.showError(
                EventStatus.SearchError(
                    _context!!.getString(R.string.enter_city_name_toast)
                )
            )

            return
        }

        weatherUseCase.getLocalWeatherData(cityName) { status ->
            when (status) {

                is ResponseUseCase.Success -> _view!!.showWeatherData(status.value)


                is ResponseUseCase.Error -> _view!!.showError(
                    EventStatus.UpdateError(
                        _context!!.getString(R.string.update_error_toast)
                    )
                )


                else -> return@getLocalWeatherData

            }
        }
    }

    override fun updateWeather() {
        weatherUseCase.updateWeather { response ->
            when (response) {
                is ResponseUseCase.Success -> _view!!.showWeatherData(response.value)

                is ResponseUseCase.Error -> _view!!.showError(
                    EventStatus.UpdateError(
                        _context!!.getString(R.string.update_error_toast)
                    )
                )

                is ResponseUseCase.Failed -> _view!!.showError(
                    EventStatus.UpdateError(
                        _context!!.getString(R.string.enter_city_name_toast)
                    )
                )
            }
        }
    }


}