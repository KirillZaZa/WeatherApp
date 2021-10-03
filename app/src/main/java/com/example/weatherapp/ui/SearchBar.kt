package com.example.weatherapp.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.weatherapp.R

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): LinearLayout(context, attrs, defStyleAttrs) {

    init {
        View.inflate(context, R.layout.search_bar, this)
        setBackground()
    }



    private fun setBackground(){
        val bg = ContextCompat.getDrawable(context, R.drawable.shape_search)
        background = bg
    }


}