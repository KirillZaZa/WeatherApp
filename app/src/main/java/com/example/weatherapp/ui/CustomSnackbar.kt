package com.example.weatherapp.ui

import android.content.Context
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.postDelayed
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CustomSnackbarBinding
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject
import java.util.concurrent.TimeUnit

class CustomSnackbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    private val viewBinding by viewBinding(CustomSnackbarBinding::bind)

    private var endX: Int = 0

    init {
        View.inflate(context, R.layout.custom_snackbar, this)
        setBackground()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        endX = this.height
    }

    private fun setBackground() {
        val materialBg = ContextCompat.getDrawable(context, R.drawable.shape_search)
        background = materialBg
        elevation = 10f
    }


    fun showToastMessage(msg: String) {
        viewBinding.msg.text = msg
        show(250, 200f, View.VISIBLE)
            .andThen(show(250, -endX.toFloat(), View.GONE))
            .subscribe()

    }


    private fun View.show(duration: Long, translation: Float, visibility: Int): Completable {
        val animationSubject = CompletableSubject.create()
        return when (visibility) {
            View.GONE -> {
                animationSubject.doOnSubscribe {
                    ViewCompat.animate(this)
                        .translationY(translation)
                        .setDuration(duration)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .withEndAction {
                            this.visibility = visibility
                        }.start()
                }
            }
            View.VISIBLE -> {
                animationSubject.doOnSubscribe {
                    ViewCompat.animate(this)
                        .translationY(translation)
                        .setDuration(duration)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .withStartAction {
                            this.visibility = visibility
                            postDelayed({
                                animationSubject.onComplete()
                            }, 1000)
                        }.start()
                }
            }
            else -> return animationSubject.doOnSubscribe {  }

        }

    }

}


