package com.example.weatherapp.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import javax.inject.Inject

class Keyboard @Inject constructor(){


    fun openKeyboard(view: View){
        if(view.requestFocus()){
            val inputManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

}