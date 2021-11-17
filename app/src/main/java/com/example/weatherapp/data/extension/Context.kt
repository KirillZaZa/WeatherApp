package com.example.weatherapp.data.extension

import android.content.Context
import android.util.TypedValue
import com.example.weatherapp.R


fun Context.dpToPx(dp: Int): Int{
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    ).toInt()
}