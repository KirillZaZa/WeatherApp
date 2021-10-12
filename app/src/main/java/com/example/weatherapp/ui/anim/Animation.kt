package com.example.weatherapp.ui.anim

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.view.ViewCompat


fun View.runUpdateAnimation() {
    ViewCompat.animate(this)
        .rotationBy(180f)
        .setDuration(250)
        .setInterpolator(LinearInterpolator())
        .start()
}
