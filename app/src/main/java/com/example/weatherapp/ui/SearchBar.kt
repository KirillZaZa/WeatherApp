package com.example.weatherapp.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.SearchBarBinding

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    private val _viewBinding by viewBinding(SearchBarBinding::bind)

    val searchBinding: SearchBarBinding
        get() = _viewBinding

    init {
        View.inflate(context, R.layout.search_bar, this)
        setBackground()

    }


    private fun setBackground() {
        val bg = ContextCompat.getDrawable(context, R.drawable.shape_search)
        background = bg
    }


}