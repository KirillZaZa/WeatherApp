package com.example.weatherapp.data.local.prefs

import android.content.Context
import javax.inject.Inject

class PrefsManager @Inject constructor(private val context: Context) {


    companion object {
        private const val PREFS_MANAGER_KEY = "prefs_key"
        private const val CITY_NAME_PREFS_KEY = "city_name_prefs_key"
    }

    fun getCityFromPrefs(): String {
        val prefs = context.getSharedPreferences(PREFS_MANAGER_KEY, Context.MODE_PRIVATE)

        return prefs.getString(CITY_NAME_PREFS_KEY, "Санкт-Петербург")!!
    }

    fun setCityToPrefs(cityName: String){
        val prefs = context.getSharedPreferences(PREFS_MANAGER_KEY, Context.MODE_PRIVATE)

        with(prefs.edit()){
            putString(CITY_NAME_PREFS_KEY, cityName)
        }.apply()
    }


}