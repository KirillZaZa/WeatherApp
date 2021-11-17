package com.example.weatherapp.presentation.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.local.entity.LocalWeatherHourly
import com.example.weatherapp.presentation.holders.HourlyViewHolder

class HourlyWeatherAdapter :
    RecyclerView.Adapter<HourlyViewHolder>() {

    private lateinit var initList: MutableList<LocalWeatherHourly>
    private var _isLoading: Boolean = true

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<LocalWeatherHourly>, isLoading: Boolean) {
        _isLoading = isLoading
        initList = newList as MutableList<LocalWeatherHourly>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_degrees_per_hour, parent, false)

        return HourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val itemHourly = initList[position]
        Log.e("Adapter", "onBindViewHolder: $_isLoading", )

        with(holder) {
            itemImage.setImageResource(itemHourly.weatherType?.resourceImageId ?: 0)
            itemTextTime.text = itemHourly.time ?: ""
            itemTextTemperature.text = itemHourly.temperature ?: ""
            if (_isLoading) {
                itemShimmer.showShimmer(true)
            }
            else {
                itemShimmer.stopShimmer()
                itemShimmer.hideShimmer()
            }
        }
    }

    override fun getItemCount(): Int {
        return initList.size
    }


}