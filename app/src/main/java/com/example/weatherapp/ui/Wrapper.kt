package com.example.weatherapp.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.BottomWrapperBinding

class Wrapper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    private val viewBinding by viewBinding(BottomWrapperBinding::bind)
    private var centerX: Int = 0
    private val searchBinding
        get() = viewBinding.searchBar.searchBinding
    val wrapperBinding: BottomWrapperBinding
        get() = viewBinding

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = this.height / 2
        super.onSizeChanged(w, h, oldw, oldh)
    }

    init {
        View.inflate(context, R.layout.bottom_wrapper, this)
        setBackground()

    }

    private fun setBackground() {
        val materialBg = ContextCompat.getDrawable(context, R.drawable.shape_wrapper)
        background = materialBg
    }

    fun showSearchBar(parentHeight: Int) {
        Log.e("Wrapper", "showSearchBar: animate")
        val tY = (parentHeight / 3.4).toFloat()
        ViewCompat.animate(viewBinding.searchBar)
            .translationY(-tY)
            .setDuration(250)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
        searchBinding.searchEditText.isOpened = true
    }

    fun closeSearchBar() {
        if(searchBinding.searchEditText.isOpened){
            Log.e("Wrapper", "closeSearchBar: animate")

            ViewCompat.animate(viewBinding.searchBar)
                .translationY(0f)
                .setDuration(250)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }
    }


}