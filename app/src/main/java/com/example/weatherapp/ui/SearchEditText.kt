package com.example.weatherapp.ui

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText

class SearchEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
): AppCompatEditText(context, attrs, defStyleAttrs) {

    private lateinit var keyImeChangeListener: KeyImeChange
    var isOpened = true

    interface KeyImeChange{
        fun onKeyIme(keyCode: Int, event: KeyEvent?)
    }

    fun setOnKeyImeChangeListener(listener: KeyImeChange){
        keyImeChangeListener = listener
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {

        if(keyCode == KeyEvent.KEYCODE_BACK && event!!.action == KeyEvent.ACTION_DOWN){
            keyImeChangeListener.onKeyIme(keyCode, event)
            isOpened = !isOpened
        }

        return super.onKeyPreIme(keyCode, event)
    }




}