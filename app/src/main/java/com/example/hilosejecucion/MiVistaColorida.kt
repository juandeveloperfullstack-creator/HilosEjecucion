package com.example.hilosejecucion

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class MiVistaColorida @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        // Color de fondo inicial por defecto
        setBackgroundColor(Color.LTGRAY)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            // Generar un color RGB aleatorio cada vez que se toca la vista
            val colorAleatorio = Color.rgb(
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256)
            )
            setBackgroundColor(colorAleatorio)
            return true
        }
        return super.onTouchEvent(event)
    }
}