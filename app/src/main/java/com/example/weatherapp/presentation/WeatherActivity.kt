package com.example.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.application.appComponent
import com.example.weatherapp.data.local.WeatherType
import com.example.weatherapp.data.local.entity.LocalWeatherData
import com.example.weatherapp.data.local.entity.LocalWeatherHourly
import com.example.weatherapp.data.local.prefs.PrefsManager
import com.example.weatherapp.databinding.ActivityWeatherBinding
import com.example.weatherapp.presentation.adapters.HourlyWeatherAdapter
import com.example.weatherapp.presentation.holders.ItemOffsetDecoration
import com.example.weatherapp.presentation.presenters.WeatherPresenterImpl
import com.example.weatherapp.presentation.view.WeatherView
import com.example.weatherapp.presentation.view.status.EventStatus
import com.example.weatherapp.ui.SearchEditText
import com.example.weatherapp.ui.anim.runUpdateAnimation
import com.example.weatherapp.utils.Keyboard
import com.example.weatherapp.utils.WeatherUtil
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), WeatherView, View.OnClickListener,
    SearchEditText.KeyImeChange {

    private val viewBinding by viewBinding(ActivityWeatherBinding::bind)
    private val searchBarBinding
        get() = viewBinding
            .wrapper
            .wrapperBinding
            .searchBar
            .searchBinding
    private val wrapperBinding
        get() = viewBinding.wrapper.wrapperBinding

    @Inject
    lateinit var weatherPresenterImpl: WeatherPresenterImpl

    @Inject
    lateinit var keyboard: Keyboard

    private lateinit var searchEditText: SearchEditText

    private lateinit var rvAdapter: HourlyWeatherAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        rvAdapter = HourlyWeatherAdapter()
        wrapperBinding.wrapperRv.addItemDecoration(ItemOffsetDecoration(12))
        wrapperBinding.wrapperRv.adapter = rvAdapter

        appComponent.inject(this)



        setupSearch()
        setListeners()
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            viewBinding.buttonUpdate.isEnabled = !isLoading
            viewBinding.buttonUpdate.runUpdateAnimation()

            with(viewBinding){
                tvWindInfo.text = getString(R.string.text_info_error)
                tvTemperatureInfo.text = getString(R.string.text_info_error)
                tvHumidityInfo.text = getString(R.string.text_info_error)
                tvCurrentWeather.text = getString(R.string.text_loading)
            }

            val list = mutableListOf<LocalWeatherHourly>()
            val item = LocalWeatherHourly()

            repeat(24){
                list.add(it, item)
            }
            rvAdapter.setData(list, true)
        } else viewBinding.buttonUpdate.isEnabled = !isLoading
    }


    override fun onClick(view: View?) {
        view ?: return
        when (view) {

            viewBinding.buttonUpdate -> weatherPresenterImpl.updateWeather() { showLoading(it) }

            searchBarBinding.searchEditText -> openSearch()
        }
    }

    private fun setListeners() {
        viewBinding.buttonUpdate.setOnClickListener(this)
        searchEditText.setOnClickListener(this)
    }

    override fun toast(msg: String) {
        viewBinding.customSnackbar.showToastMessage(msg)
    }

    override fun showError(eventStatus: EventStatus) {
        toast(eventStatus.msg)
        with(viewBinding) {
            tvWindInfo.text = getString(R.string.text_info_error)
            tvTemperatureInfo.text = getString(R.string.text_info_error)
            tvHumidityInfo.text = getString(R.string.text_info_error)
            tvCurrentWeather.text = getString(R.string.text_error)
            ivWeather.setImageResource(R.drawable.ic_sad)
        }

    }


    override fun showWeatherData(localWeatherData: LocalWeatherData) {
        toast(getString(R.string.updated_toast))

        with(viewBinding) {
            tvDate.text = WeatherUtil.getCurrentDate()
            tvCity.text = localWeatherData.cityName

            tvCurrentWeather.text = localWeatherData.weather
            tvTemperatureInfo.text = localWeatherData.temperature
            tvHumidityInfo.text = localWeatherData.humidity
            tvWindInfo.text = localWeatherData.windSpeed

            ivWeather.setImageResource(localWeatherData.weatherType.resourceImageId)
        }

    }

    override fun showForecastForDay(hourlyList: List<LocalWeatherHourly>) {
        rvAdapter.setData(newList = hourlyList, false)
    }




    private fun setupSearch() {
        searchEditText = searchBarBinding.searchEditText
        searchEditText.setOnKeyImeChangeListener(this)
    }

    private fun openSearch() {
        viewBinding.wrapper.showSearchBar(viewBinding.root.height)
        searchEditText.isFocusableInTouchMode = true
        searchEditText.isFocusable = true
        searchEditText.requestFocus()

        keyboard.openKeyboard(searchEditText)
    }

    private fun closeSearch() {
        searchEditText.clearFocus()
        searchEditText.isFocusable = false
        viewBinding.wrapper.closeSearchBar()
    }


    override fun onResume() {
        super.onResume()
        weatherPresenterImpl.onAttach(this, this)
        weatherPresenterImpl.updateWeather { showLoading(it) }
    }


    override fun onKeyIme(keyCode: Int, event: KeyEvent?) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event!!.action == KeyEvent.ACTION_DOWN)
            closeSearch()
        else if (keyCode == EditorInfo.IME_ACTION_GO) {
            if (searchEditText.text.isNullOrBlank()) return

            weatherPresenterImpl.searchButtonClick(
                searchEditText.text?.trim().toString()
            )
        }
    }


}