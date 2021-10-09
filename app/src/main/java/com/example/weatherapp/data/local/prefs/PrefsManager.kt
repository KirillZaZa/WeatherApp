package com.example.weatherapp.data.local.prefs

import android.content.Context
import javax.inject.Inject

class PrefsManager @Inject constructor(private val context: Context) {


    companion object {
        private const val PREFS_MANAGER_KEY = "prefs_key"

        private const val CITY_NAME_PREFS_KEY = "city_name_prefs_key"
        private const val LAT_CITY_KEY = "lan"
        private const val LON_CITY_KEY = "lon"
    }

    fun getCityFromPrefs(): String {
        val prefs = context.getSharedPreferences(PREFS_MANAGER_KEY, Context.MODE_PRIVATE)

        return prefs.getString(CITY_NAME_PREFS_KEY, "Санкт-Петербург")!!
    }

    fun setCityToPrefs(cityName: String){
        val prefs = context.getSharedPreferences(PREFS_MANAGER_KEY, Context.MODE_PRIVATE)

        if(prefs.contains(CITY_NAME_PREFS_KEY)) return

        with(prefs.edit()){
            putString(CITY_NAME_PREFS_KEY, cityName)
        }.apply()
    }

    fun getLatFromPrefs(): String{
        val prefs = context.getSharedPreferences(PREFS_MANAGER_KEY, Context.MODE_PRIVATE)

        return prefs.getString(LAT_CITY_KEY, "33.44")!!
    }

    fun setLatToPrefs(lat: String){
        val prefs = context.getSharedPreferences(PREFS_MANAGER_KEY, Context.MODE_PRIVATE)

        if(prefs.contains(LAT_CITY_KEY)) return

        with(prefs.edit()){
            putString(LAT_CITY_KEY, lat)
        }.apply()
    }

    fun getLonFromPrefs(): String{
        val prefs = context.getSharedPreferences(PREFS_MANAGER_KEY, Context.MODE_PRIVATE)

        return prefs.getString(LON_CITY_KEY, "-94.04")!!
    }

    fun setLonFromPrefs(lon: String){
        val prefs = context.getSharedPreferences(PREFS_MANAGER_KEY, Context.MODE_PRIVATE)

        if(prefs.contains(LON_CITY_KEY)) return

        with(prefs.edit()){
            putString(LON_CITY_KEY, lon)
        }.apply()
    }


}