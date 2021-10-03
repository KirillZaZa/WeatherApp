package com.example.weatherapp.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.weatherapp.R

class Wrapper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): ConstraintLayout(context, attrs, defStyleAttrs) {


    init {
        View.inflate(context, R.layout.bottom_wrapper, this)
        setBackground()
    }

    private fun setBackground(){
        val materialBg = ContextCompat.getDrawable(context, R.drawable.shape_wrapper)
        background = materialBg
    }

}